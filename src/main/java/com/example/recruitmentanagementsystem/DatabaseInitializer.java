package com.example.recruitmentanagementsystem;

import com.example.recruitmentanagementsystem.domain.model.DegreeCourse;
import com.example.recruitmentanagementsystem.repository.DegreeCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final DegreeCourseRepository degreeCourseRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        DegreeCourse informatics = new DegreeCourse();
        informatics.setName("Informatics");
        informatics.setDepartment("WIEik");
        informatics.setNumberOfPlaces(60);

        DegreeCourse electronics = new DegreeCourse();
        electronics.setName("Electronics");
        electronics.setDepartment("WIEik");
        electronics.setNumberOfPlaces(60);

        DegreeCourse electrotechnics = new DegreeCourse();
        electrotechnics.setName("Electrotechnics");
        electrotechnics.setDepartment("WIEik");
        electrotechnics.setNumberOfPlaces(60);

        degreeCourseRepository.save(informatics);
        degreeCourseRepository.save(electronics);
        degreeCourseRepository.save(electrotechnics);
    }
}
