package com.weissdennis.tsas.tsurs.service;

import com.weissdennis.tsas.common.ts3users.TS3UserInChannel;
import com.weissdennis.tsas.common.ts3users.TS3UserInChannelImpl;
import com.weissdennis.tsas.tsurs.mapper.TS3UserInChannelMapper;
import com.weissdennis.tsas.tsurs.persistence.TS3UserInChannelRepository;
import com.weissdennis.tsas.tsurs.persistence.TS3UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TS3UserInChannelImportService {

    private final TS3UserInChannelRepository ts3UserInChannelRepository;
    private final TS3UserRepository ts3UserRepository;

    private static Logger logger = LoggerFactory.getLogger(TS3UserInChannelImportService.class);

    @Autowired
    public TS3UserInChannelImportService(TS3UserInChannelRepository ts3UserInChannelRepository,
                                         TS3UserRepository ts3UserRepository) {
        this.ts3UserInChannelRepository = ts3UserInChannelRepository;
        this.ts3UserRepository = ts3UserRepository;
    }

    @KafkaListener(topics = "ts3_user_in_channel", containerFactory = "ts3UserInChannelConcurrentKafkaListenerContainerFactory")
    public void listen(TS3UserInChannel ts3UserInChannel) {

        ts3UserInChannelRepository.save(TS3UserInChannelMapper.INSTANCE.ts3UserInChannelImplToTS3UserInChannelEntity
                ((TS3UserInChannelImpl) ts3UserInChannel));

        ts3UserRepository
                .findById(ts3UserInChannel.getUniqueId())
                .ifPresent(ts3UserEntity -> {
                    ts3UserEntity.setLastOnline(ts3UserInChannel.getDateTime());
                    ts3UserRepository.save(ts3UserEntity);
                });
    }
}
