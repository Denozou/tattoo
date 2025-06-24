package com.landing.tattoo.config;

import com.landing.tattoo.model.ContentBlock;
import com.landing.tattoo.repository.ContentBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ContentBlockRepository contentBlockRepository;

    @Autowired
    public DataInitializer(ContentBlockRepository contentBlockRepository) {
        this.contentBlockRepository = contentBlockRepository;
    }

    @Override
    public void run(String... args) {
        List<String> keys = Arrays.asList("artistry", "devotion", "passion");

        for (String key : keys) {
            if (contentBlockRepository.findByKey(key).isEmpty()) {
                ContentBlock contentBlock = new ContentBlock();
                contentBlock.setKey(key);
                contentBlock.setContentEn(getDefaultEnglishContent(key));
                contentBlock.setContentUa(getDefaultUkrainianContent(key));
                contentBlockRepository.save(contentBlock);
            }
        }
    }

    private String getDefaultEnglishContent(String key) {
        switch (key) {
            case "artistry":
                return "Our tattoo artistry is a blend of technical precision and creative vision. Each design is crafted with meticulous attention to detail, ensuring that your tattoo is not just ink on skin, but a masterpiece that tells your unique story.";
            case "devotion":
                return "We are devoted to providing an exceptional tattooing experience. From the moment you walk through our doors, our focus is on your comfort, safety, and satisfaction. We use only the highest quality materials and adhere to strict hygiene standards.";
            case "passion":
                return "Passion drives everything we do. We're not just tattoo artists; we're dedicated craftspeople who pour our heart and soul into every line, shade, and color. Your body is our canvas, and we treat it with the utmost respect and care.";
            default:
                return "";
        }
    }

    private String getDefaultUkrainianContent(String key) {
        switch (key) {
            case "artistry":
                return "Наше татуювання - це поєднання технічної точності та творчого бачення. Кожен дизайн створюється з ретельною увагою до деталей, гарантуючи, що ваше татуювання - це не просто чорнило на шкірі, а шедевр, який розповідає вашу унікальну історію.";
            case "devotion":
                return "Ми присвячуємо себе наданню виняткового досвіду татуювання. З моменту, коли ви переступаєте поріг нашого салону, наша увага зосереджена на вашому комфорті, безпеці та задоволенні. Ми використовуємо лише матеріали найвищої якості та дотримуємося суворих стандартів гігієни.";
            case "passion":
                return "Пристрасть керує всім, що ми робимо. Ми не просто тату-майстри; ми віддані ремісники, які вкладають серце і душу в кожну лінію, тінь і колір. Ваше тіло - наше полотно, і ми ставимося до нього з найбільшою повагою та турботою.";
            default:
                return "";
        }
    }
}
