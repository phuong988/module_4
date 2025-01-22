package com.example.football_player_management.aspect;

import com.example.football_player_management.exception.PlayerNotFoundException;
import com.example.football_player_management.model.Player;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PlayerLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(PlayerLogAspect.class);

    // Ghi log khi thay đổi trạng thái cầu thủ
    @AfterReturning(pointcut = "execution(* com.example.football_player_management.service.impl.PlayerService.registerPlayer(..)) || execution(* com.example.football_player_management.service.impl.PlayerService.unregisterPlayer(..))")
    public void logPlayerStatusChange(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        int playerId = (int) args[0];
        logger.info("Trạng thái cầu thủ với ID {} đã được thay đổi.", playerId);
    }

    // Ghi log số lượng cầu thủ đăng ký đá
    @AfterReturning(value = "execution(* com.example.football_player_management.service.impl.PlayerService.registerPlayer(..))", returning = "result")
    public void logRegisteredPlayersCount(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        int playerId = (int) args[0];
        logger.info("Số lượng cầu thủ đã đăng ký tăng lên sau khi đăng ký cầu thủ với ID {}.", playerId);
    }
}
