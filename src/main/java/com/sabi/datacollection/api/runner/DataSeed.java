package com.sabi.datacollection.api.runner;


import com.sabi.datacollection.core.models.Country;
import com.sabi.datacollection.core.models.LGA;
import com.sabi.datacollection.core.models.State;
import com.sabi.datacollection.service.repositories.CountryRepository;
import com.sabi.datacollection.service.repositories.LGARepository;
import com.sabi.datacollection.service.repositories.StateRepository;
import com.sabi.framework.models.User;
import com.sabi.framework.repositories.UserRepository;
import com.sabi.framework.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Component
public class DataSeed implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private LGARepository localGovernmentRepository;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;





    private Logger log = LogManager.getLogger(DataSeed.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

//        seedCountries();
//        seedStates();
//        seedLocalGovernments();
//        seedBanks();
        seedAppleUsers();
        seedSabiUsers();
        seedAdminUsers();

    }


    private void seedCountries() {
        List<Country> countries = new ArrayList<Country>() {
            {
                add(new Country("Nigeria","234"));
                add(new Country("Ghana", "233"));
                add(new Country("Kenya", "254"));
                add(new Country("Uganda", "256"));
                 add(new Country("Angola", "244"));
                 add(new Country("Cameroon", "237"));
                 add(new Country("Central African Republic", "236"));
                 add(new Country("Chad", "235"));
                 add(new Country("D.R Congo", "243"));
                 add(new Country("Republic of the Congo", "242"));
                 add(new Country("Equatorial Guinea", "240"));
                 add(new Country("Gabon", "241"));
                 add(new Country("São Tomé and Príncipe", "239"));
                 add(new Country("Burundi", "257"));
                 add(new Country("Comoros", "269"));
                 add(new Country("Madagascar", "261"));
                 add(new Country("Malawi", "265"));
                 add(new Country("Mauritius", "230"));
                 add(new Country("Mozambique", "258"));
                 add(new Country("Rwanda", "250"));
                 add(new Country("Seychelles", "248"));
                 add(new Country("Tanzania", "255"));
                 add(new Country("Uganda", "256"));
                 add(new Country("Djibouti", "253"));
                 add(new Country("Eritrea", "291"));
                 add(new Country("Ethiopia", "251"));
                 add(new Country("Somalia", "252"));
                 add(new Country("Algeria", "213"));
                 add(new Country("Egypt", "20"));
                 add(new Country("Libya", "218"));
                 add(new Country("Morocco", "212"));
                 add(new Country("South Sudan", "211"));
                 add(new Country("Sudan", "249"));
                 add(new Country("Tunisia", "216"));
                 add(new Country("Botswana", "267"));
                 add(new Country("Lesotho", "266"));
                 add(new Country("Namibia", "264"));
                 add(new Country("South Africa", "27"));
                 add(new Country("Zambia", "260" ));
                 add(new Country("Zimbabwe", "263"));
                 add(new Country("Benin", "229"));
                 add(new Country("Burkina Faso", "226"));
                 add(new Country("Cape Verde", "238"));
                 add(new Country("Ivory Coast", "225"));
                 add(new Country("Gambia", "220"));
                 add(new Country("Guinea", "224"));
                 add(new Country("Guinea-Bissau", "245"));
                 add(new Country("Liberia", "231"));
                 add(new Country("Mali", "223"));
                 add(new Country("Mauritania", "222"));
                 add(new Country("Niger", "227"));
                 add(new Country("Senegal", "221"));
                 add(new Country("Sierra Leone", "232"));
                 add(new Country("Togo", "228"));
            }
        };

        countries.forEach(country -> {
            Country fetchCountry = countryRepository.findByName(country.getName());
            if (fetchCountry == null) {
                countryRepository.saveAndFlush(country);
            }
        });
    }



    private void seedStates() {
        List<State> states = new ArrayList<State>() {
            {
                add(new State("Abia", 1l));
                add(new State("Adamawa",1l));
                add(new State("Akwa Ibom",1l));
                add(new State("Anambra",1l));
                add(new State("Bauchi",1l));
                add(new State("Bayelsa",1l));
                add(new State("Benue",1l));
                add(new State("Borno",1l));
                add(new State("Cross River",1l));
                add(new State("Delta",1l));
                add(new State("Ebonyi",1l));
                add(new State("Edo",1l));
                add(new State("Ekiti",1l));
                add(new State("Enugu",1l));
                add(new State("FCT Abuja",1l));
                add(new State("Gombe",1l));
                add(new State("Imo",1l));
                add(new State("Jigawa",1l));
                add(new State("Kaduna",1l));
                add(new State("Kano",1l));
                add(new State("Katsina",1l));
                add(new State("Kebbi",1l));
                add(new State("Kogi",1l));
                add(new State("Kwara",1l));
                add(new State("Lagos",1l));
                add(new State("Nasarawa",1l));
                add(new State("Niger",1l));
                add(new State("Ogun",1l));
                add(new State("Ondo",1l));
                add(new State("Osun",1l));
                add(new State("Oyo",1l));
                add(new State("Plateau",1l));
                add(new State("Rivers",1l));
                add(new State("Sokoto",1l));
                add(new State("Taraba",1l));
                add(new State("Yobe",1l));
                add(new State("Zamfara",1l));
            }
        };

        states.forEach(state -> {
            State fetchState = stateRepository.findByName(state.getName());
            if (fetchState == null) {
                stateRepository.saveAndFlush(state);
            }
        });
    }







    private void seedLocalGovernments() {
        List<LGA> localGovernments = new ArrayList<LGA>() {
            {
                add(new LGA("Aba North", 1l));
                add(new LGA("Aba South", 1l));
                add(new LGA("Arochukwu", 1l));
                add(new LGA("Bende", 1l));
                add(new LGA("Ikwuano", 1l));
                add(new LGA("Isiala Ngwa North", 1l));
                add(new LGA("Isiala Ngwa South", 1l));
                add(new LGA("Isuikwuato", 1l));
                add(new LGA("Obingwa", 1l));
                add(new LGA("Ohafia", 1l));
                add(new LGA("Osisioma", 1l));
                add(new LGA("Ugwunagbo", 1l));
                add(new LGA("Ukwa East", 1l));
                add(new LGA("Ukwa  West", 1l));
                add(new LGA("Umuahia North", 1l));
                add(new LGA("Umuahia  South", 1l));
                add(new LGA("Umu - Nneochi", 1l));

                add(new LGA("Demsa", 2l));
                add(new LGA("Fufore", 2l));
                add(new LGA("Ganye", 2l));
                add(new LGA("Gire 1", 2l));
                add(new LGA("Gombi", 2l));
                add(new LGA("Guyuk", 2l));
                add(new LGA("Hong", 2l));
                add(new LGA("Jada", 2l));
                add(new LGA("Lamurde", 2l));
                add(new LGA("Madagali", 2l));
                add(new LGA("Maiha", 2l));
                add(new LGA("Mayo - Belwa", 2l));
                add(new LGA("Michika", 2l));
                add(new LGA("Mubi North", 2l));
                add(new LGA("Mubi South", 2l));
                add(new LGA("Numan", 2l));
                add(new LGA("Shelleng", 2l));
                add(new LGA("Song", 2l));
                add(new LGA("Toungo", 2l));
                add(new LGA("Yola North", 2l));
                add(new LGA("Yola South", 2l));

                add(new LGA("Abak", 3L));
                add(new LGA("Eastern Obolo", 3L));
                add(new LGA("Eket", 3L));
                add(new LGA("Esit Eket (Uquo)", 3L));
                add(new LGA("Essien Udim", 3L));
                add(new LGA("Etim Ekpo", 3L));
                add(new LGA("Etinan", 3L));
                add(new LGA("Ibeno", 3L));
                add(new LGA("Ibesikpo Asutan", 3L));
                add(new LGA("Ibiono Ibom", 3L));
                add(new LGA("Ika", 3L));
                add(new LGA("Ikono", 3L));
                add(new LGA("Ikot Abasi", 3L));
                add(new LGA("Ikot Ekpene", 3L));
                add(new LGA("Ini", 3L));
                add(new LGA("Itu", 3L));
                add(new LGA("Mbo", 3L));
                add(new LGA("Mkpat Enin", 3L));
                add(new LGA("Nsit Atai", 3L));
                add(new LGA("Nsit Ibom", 3L));
                add(new LGA("Nsit Ubium", 3L));
                add(new LGA("Obot Akara", 3L));
                add(new LGA("Okobo", 3L));
                add(new LGA("Onna", 3L));
                add(new LGA("Oron", 3L));
                add(new LGA("Oruk Anam", 3L));
                add(new LGA("Udung Uko", 3L));
                add(new LGA("Ukanafun", 3L));
                add(new LGA("Uruan", 3L));
                add(new LGA("Urue Offong/Oruko", 3L));
                add(new LGA("Uyo", 3L));

                add(new LGA("Aguata", 4l));
                add(new LGA("Ayamelum", 4l));
                add(new LGA("Anambra East", 4l));
                add(new LGA("Anambra West", 4l));
                add(new LGA("Anaocha", 4l));
                add(new LGA("Awka North", 4l));
                add(new LGA("Awka South", 4l));
                add(new LGA("Dunukofia", 4l));
                add(new LGA("Ekwusigo", 4l));
                add(new LGA("Idemili North", 4l));
                add(new LGA("Idemili-South", 4l));
                add(new LGA("Ihala", 4l));
                add(new LGA("Njikoka", 4l));
                add(new LGA("Nnewi North", 4l));
                add(new LGA("Nnewi South", 4l));
                add(new LGA("Ogbaru", 4l));
                add(new LGA("Onitsha-North", 4l));
                add(new LGA("Onitsha -South", 4l));
                add(new LGA("Orumba North", 4l));
                add(new LGA("Orumba  South", 4l));
                add(new LGA("Oyi", 4l));

                add(new LGA("Alkaleri", 5l));
                add(new LGA("Bauchi", 5l));
                add(new LGA("Bogoro", 5l));
                add(new LGA("Dambam", 5l));
                add(new LGA("Darazo", 5l));
                add(new LGA("Dass", 5l));
                add(new LGA("Gamawa", 5l));
                add(new LGA("Ganjuwa", 5l));
                add(new LGA("Giade", 5l));
                add(new LGA("Itas/Gadau", 5l));
                add(new LGA("Jama'Are", 5l));
                add(new LGA("Katagum", 5l));
                add(new LGA("Kirfi", 5l));
                add(new LGA("Misau", 5l));
                add(new LGA("Ningi", 5l));
                add(new LGA("Shira", 5l));
                add(new LGA("Tafawa Balewa", 5l));
                add(new LGA("Toro", 5l));
                add(new LGA("Warji", 5l));
                add(new LGA("Zaki", 5l));

                add(new LGA("Brass", 6l));
                add(new LGA("Ekeremor", 6l));
                add(new LGA("Kolokuma/Opokuma", 6l));
                add(new LGA("Nembe", 6l));
                add(new LGA("Ogbia", 6l));
                add(new LGA("Sagbama", 6l));
                add(new LGA("Southern Ijaw", 6l));
                add(new LGA("Yenagoa", 6l));

                add(new LGA("Ado", 7l));
                add(new LGA("Agatu", 7l));
                add(new LGA("Apa", 7l));
                add(new LGA("Buruku", 7l));
                add(new LGA("Gboko", 7l));
                add(new LGA("Guma", 7l));
                add(new LGA("Gwer East", 7l));
                add(new LGA("Gwer West", 7l));
                add(new LGA("Katsina-Ala", 7l));
                add(new LGA("Konshisha", 7l));
                add(new LGA("Kwande", 7l));
                add(new LGA("Logo", 7l));
                add(new LGA("Makurdi", 7l));
                add(new LGA("Obi", 7l));
                add(new LGA("Ogbadibo", 7l));
                add(new LGA("Oju", 7l));
                add(new LGA("Ohimini", 7l));
                add(new LGA("Okpokwu", 7l));
                add(new LGA("Otukpo", 7l));
                add(new LGA("Tarka", 7l));
                add(new LGA("Ukum", 7l));
                add(new LGA("Ushongo", 7l));
                add(new LGA("Vandeikya", 7l));

                add(new LGA("Agege", 25l));
                add(new LGA("Ajeromi/Ifelodun", 25l));
                add(new LGA("Alimosho", 25l));
                add(new LGA("Amuwo-Odofin", 25l));
                add(new LGA("Apapa", 25l));
                add(new LGA("Badagry", 25l));
                add(new LGA("Epe", 25l));
                add(new LGA("Eti-Osa", 25l));
                add(new LGA("Ibeju/Lekki", 25l));
                add(new LGA("Ifako-Ijaye", 25l));
                add(new LGA("Ikeja", 25l));
                add(new LGA("Ikorodu", 25l));
                add(new LGA("Kosofe", 25l));
                add(new LGA("Lagos Island", 25l));
                add(new LGA("Lagos Mainland", 25l));
                add(new LGA("Mushin", 25l));
                add(new LGA("Ojo", 25l));
                add(new LGA("Oshodi/Isolo", 25l));
                add(new LGA("Somolu", 25l));
                add(new LGA("Surulere", 25l));
            }
        };
        localGovernments.forEach(localGovernment -> {
            LGA fetchLocalGovernment = localGovernmentRepository.findByName(localGovernment.getName());
            if (fetchLocalGovernment == null) {
                localGovernmentRepository.saveAndFlush(localGovernment);
            }
        });
    }



    private void seedAppleUsers() {
        User user = userRepo.findByEmail("appleUser@sabi.com");
        if (user == null) {
            createUser();
        }
    }


    private User createUser() {
        User appleUser = new User();
        appleUser.setFirstName("appleUser");
        appleUser.setLastName("appleUser2");
        appleUser.setPassword(passwordEncoder.encode("000000"));
        appleUser.setPhone("01156548654");
        appleUser.setEmail("appleUser@sabi.com");
        appleUser.setUsername("appleUser@sabi.com");
        appleUser.setLoginAttempts(0);
        appleUser.setUserCategory(Constants.OTHER_USER);
        appleUser.setIsActive(true);
        appleUser.setPasswordChangedOn(LocalDateTime.now());
        appleUser.setCreatedBy(0L);
        appleUser.setCreatedDate(LocalDateTime.now());
        appleUser.setUpdatedDate(LocalDateTime.now());
        userRepo.saveAndFlush(appleUser);
        return appleUser;
    }


    private void seedSabiUsers() {
        User user = userRepo.findByEmail("sabi@sabi.com");
        if (user == null) {
            createSabiUser();
        }
    }

    private User createSabiUser() {
        User sabiUser = new User();
        sabiUser.setFirstName("sabi");
        sabiUser.setLastName("sabi2");
        sabiUser.setPassword(passwordEncoder.encode("777777"));
        sabiUser.setPhone("02163976228");
        sabiUser.setEmail("sabi@sabi.com");
        sabiUser.setUsername("sabi@sabi.com");
        sabiUser.setLoginAttempts(0);
        sabiUser.setUserCategory(Constants.ADMIN_USER);
        sabiUser.setIsActive(true);
        sabiUser.setPasswordChangedOn(LocalDateTime.now());
        sabiUser.setCreatedBy(0L);
        sabiUser.setCreatedDate(LocalDateTime.now());
        sabiUser.setUpdatedDate(LocalDateTime.now());
        userRepo.saveAndFlush(sabiUser);
        return sabiUser;
    }


    private void seedAdminUsers() {
        User user = userRepo.findByEmail("admin@sabi.com");
        if (user == null) {
            createAdminUser();
        }
    }

    private User createAdminUser() {
        User user = new User();
        user.setFirstName("adminUser");
        user.setLastName("adminUser2");
        user.setPassword(passwordEncoder.encode("1111111"));
        user.setPhone("08136529363");
        user.setEmail("admin@sabi.com");
        user.setUsername("admin@sabi.com");
        user.setLoginAttempts(0);
        user.setUserCategory(Constants.ADMIN_USER);
        user.setIsActive(true);
        user.setPasswordChangedOn(LocalDateTime.now());
        user.setCreatedBy(0L);
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        userRepo.save(user);
        return user;
    }
}
