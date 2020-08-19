package cn.bdqn.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AddGoodsTask {


    @Scheduled(cron = "0/5 * * * * ?")
    public void addGoods() {

    }
}
