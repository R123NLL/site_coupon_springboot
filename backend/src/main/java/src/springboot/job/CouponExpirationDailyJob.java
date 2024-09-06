package src.springboot.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import src.springboot.repositories.CouponRepository;
import src.springboot.entities.Coupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Date;
import java.time.LocalDate;


@Component
@EnableScheduling
public class CouponExpirationDailyJob implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(CouponExpirationDailyJob.class);

    @Autowired
    private CouponRepository couponRepository;

    public CouponExpirationDailyJob() {

    }

    @Override
    @Scheduled(cron = "0 0 1 * * *") // At 01:00AM each day
    public void run() {

        LocalDate now = LocalDate.now();
        Date sqlNow = Date.valueOf(now);

        logger.info("Running CouponExpirationDailyJob at {}", sqlNow);

        for (Coupon coupon : couponRepository.findAll()) {
            if (now.isAfter(coupon.getEndDate())) {
                try {
                    couponRepository.delete(coupon);
                    logger.info("Deleted expired coupon: {}", coupon);
                } catch (Exception e) {
                    logger.error("Error deleting coupon: {}", coupon, e);
                }
            }
        }
        logger.info("CouponExpirationDailyJob completed at {}", sqlNow);
    }
}
