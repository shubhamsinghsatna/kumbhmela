package com.mahakumbh.dishanirdesh.misc;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mahakumbh.dishanirdesh.database.EntityLocationManager;
import com.mahakumbh.dishanirdesh.models.EntityLocationModel;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    private final Context context;
    private final EntityLocationManager dataManager;

    public DataInitializer(Context context) {
        this.context = context;
        dataManager = new EntityLocationManager(context);

        if (dataManager.getCount() == 0) {
            saveData();
        }
    }

    private void saveData() {
        Toast.makeText(context, "Data Insertion Progress", Toast.LENGTH_SHORT).show();
        saveGhatLocations();
        saveTempleLocations();
        saveHotelLocations();
        saveParkingLocations();
    }

    private void saveGhatLocations() {
        List<EntityLocationModel> locationModels = new ArrayList<>();

        locationModels.add(new EntityLocationModel(1, "Sangam Ghat",
                "The confluence of three rivers—Ganga, Yamuna, and the mythical Saraswati. Main site for rituals, bathing, and the Kumbh Mela.",
                 25.4297, 81.8853, "rituals, sacred, rivers, Kumbh Mela"));

        locationModels.add(new EntityLocationModel(2, "Rambagh Ghat",
                "A serene ghat near the Yamuna, often visited for peaceful experiences.",
                25.4350,  81.8700, "peaceful, serene, Yamuna"));

        locationModels.add(new EntityLocationModel(3, "Daraganj Ghat",
                "A famous ghat for performing pind daan (rituals for ancestors). Historical temples and cultural importance.",
                25.4485, 81.8780, "pind daan, rituals, history, culture"));

        locationModels.add(new EntityLocationModel(4, "Kydganj Ghat",
                "A prominent location for rituals and ceremonies. Nearby religious sites.",
                 25.4312, 81.8654, "rituals, ceremonies, religious sites"));

        locationModels.add(new EntityLocationModel(5, "Nagvasuki Ghat",
                "Associated with the Nag Vasuki temple, dedicated to the serpent king.",
                25.4542, 81.8786, "Nag Vasuki temple, serpent king, mythology"));

        locationModels.add(new EntityLocationModel(6, "Gaughat (Cow Ghat)",
                "Known for its name derived from cows and its connection to ancient practices.",
                25.433113, 81.845235, "cows, ancient practices"));

        locationModels.add(new EntityLocationModel(7, "Meerapur Ghat",
                "Used for traditional rituals and located in a quiet area.",
                25.4325,  81.8600, "traditional rituals, quiet"));

        locationModels.add(new EntityLocationModel(8, "Phaphamau Ghat",
                "Located near the Yamuna bridge and often used by locals for rituals.",
                25.4920,  81.8500, "Yamuna bridge, rituals, locals"));

        locationModels.add(new EntityLocationModel(9, "Teliyarganj Ghat",
                "Less crowded and preferred for peaceful bathing and rituals.",
                25.4600,  81.8700, "peaceful, bathing, rituals"));

        locationModels.add(new EntityLocationModel(10, "Bairagi Ghat",
                "Used by saints (Bairagis) and devotees for spiritual practices.",
                25.4250,  81.8850, "saints, spiritual, devotees"));

        locationModels.add(new EntityLocationModel(11, "Alopi Bagh Ghat",
                "Near the Alopi Devi temple, a Shakti Peeth with historical importance.",
                25.4300,  81.8600, "Alopi Devi temple, Shakti Peeth, historical"));

        locationModels.add(new EntityLocationModel(12, "Hanuman Ghat",
                "Associated with the ancient Hanuman temple submerged during the monsoons.",
                25.4280, 81.8800, "Hanuman temple, ancient, monsoons"));

        locationModels.add(new EntityLocationModel(13, "Kila Ghat",
                "Near the Allahabad Fort, this ghat holds historical relevance.",
                25.4300,  81.8700, "Allahabad Fort, historical"));

        locationModels.add(new EntityLocationModel(14, "Minto Park Ghat",
                "Named after Lord Minto and located near the iconic structure at Minto Park.",
                25.4310,  81.8650, "Lord Minto, iconic, Minto Park"));


        for (EntityLocationModel model : locationModels) {
            model.setCategory(LocationCategories.GHAT.name());
            Log.e("ghat","ghat: "+model.getTitle());
            dataManager.insert(model);
        }

    }

    private void saveTempleLocations() {

        List<EntityLocationModel> locationModels = new ArrayList<>();

        locationModels.add(new EntityLocationModel(15, "Alopi Devi Temple",
                "Believed to be one of the Shakti Peethas, this temple is unique as it doesn't house a traditional idol but a wooden cart, symbolizing the goddess. It's located in the Alopibagh area and is especially revered during the Navratri festival.",
                25.4350,81.8460, "Shakti Peetha, wooden cart, goddess, Navratri"));

        locationModels.add(new EntityLocationModel(16, "Mankameshwar Temple",
                "Situated near the Saraswati Ghat, this ancient temple is dedicated to Lord Shiva. It's believed that wishes are fulfilled for those who pray here with a pure heart.",
                 25.4290,  81.8830, "Lord Shiva, Saraswati Ghat, wishes, ancient"));

        locationModels.add(new EntityLocationModel(17, "Nag Vasuki Temple",
                "Located in Daraganj on the banks of the Ganges, this temple is dedicated to the serpent deity, Vasuki. It also houses idols of other deities, including a reclining statue of Bhishma from the Mahabharata. A significant fair is held here annually on Nag Panchami.",
                 25.4542,81.8786, "Nag Vasuki, serpent deity, Bhishma, Mahabharata, Nag Panchami"));

        locationModels.add(new EntityLocationModel(18, "Lalita Devi Temple",
                "Situated in Meerapur, this temple is considered one of the 51 Siddha Shakti Peethas. It's believed that the finger of Sati fell here, making it a revered site for devotees.",
                25.4325, 81.8600, "Siddha Shakti Peetha, Sati, Meerapur"));

        locationModels.add(new EntityLocationModel(19, "Veni Madhav Temple",
                "Also known as Lakshmi Narayan Temple, it's located in the Daraganj locality on the banks of the Ganga. The temple enshrines black stone idols of Radha and Krishna. It's believed that without having 'darshan' of Veni Madhav, the virtues accruing from a holy dip in the Ganga remain incomplete.",
                25.4485,  81.8780, "Lakshmi Narayan, Radha Krishna, darshan, Ganga"));

        locationModels.add(new EntityLocationModel(20, "Padila Mahadev Temple",
                "Located northeast of Phaphamau, this stone temple is dedicated to Lord Shiva. It's particularly popular during the month of Phalguna, especially on Shivratri.",
                25.4920, 81.8500, "Lord Shiva, Phalguna, Shivratri"));

        locationModels.add(new EntityLocationModel(21, "Akshayavat",
                "Known as the 'Indestructible Banyan Tree,' it's located within the Patalpuri Temple inside the Allahabad Fort. It's a sacred fig tree believed to be eternal and holds immense religious significance.",
                25.4284, 81.8867, "Indestructible Banyan Tree, sacred, Patalpuri, Allahabad Fort"));

        locationModels.add(new EntityLocationModel(22, "Hanuman Temple",
                "Situated near the Sangam area, this temple is unique for its reclining idol of Lord Hanuman. During the monsoon season, the temple often gets submerged due to rising water levels, which is considered a significant event by devotees.",
                 25.4297,  81.8853, "Hanuman, reclining idol, monsoon, Sangam"));

        locationModels.add(new EntityLocationModel(23, "Bhardwaj Ashram",
                "This ancient ashram is believed to be the residence of Sage Bhardwaj. It's a significant center for learning and meditation, attracting scholars and devotees alike.",
                 25.4350, 81.8460, "Bhardwaj, ancient, learning, meditation"));


        for (EntityLocationModel model : locationModels) {
            model.setCategory(LocationCategories.MANDIR.name());
            Log.e("ghat","mandir: "+model.getTitle());
            dataManager.insert(model);
        }

    }

    private void saveHotelLocations() {
        List<EntityLocationModel> locationModels = new ArrayList<>();

        locationModels.add(new EntityLocationModel(24, "WelcomHeritage Badi Kothi",
                "A heritage property offering a blend of traditional architecture and modern amenities. Guests can experience the rich cultural history of Prayagraj during their stay.",
                 25.4485,  81.8780, "heritage, traditional architecture, cultural history, modern amenities"));

        locationModels.add(new EntityLocationModel(25, "FabHotel Stay Inn I",
                "A budget-friendly hotel providing comfortable accommodations with essential amenities, suitable for travelers looking for convenience and value.",
                25.4350,  81.8460, "budget-friendly, essential amenities, convenience, value"));

        locationModels.add(new EntityLocationModel(26, "The Legend Hotel",
                "A well-appointed hotel offering modern facilities, including dining options and conference rooms, catering to both leisure and business travelers.",
                 25.4350, 81.8460, "modern facilities, dining, conference rooms, leisure, business"));

        locationModels.add(new EntityLocationModel(27, "Hotel Kanha Shyam",
                "A premium hotel featuring spacious rooms, multiple dining venues, and recreational facilities, ensuring a luxurious stay for guests.",
                 25.4350, 81.8460, "premium, spacious rooms, dining venues, recreational facilities, luxurious"));

        locationModels.add(new EntityLocationModel(28, "Grand Continental Hotel",
                "Offers comfortable accommodations with modern amenities, including a swimming pool and fitness center, providing a relaxing stay for visitors.",
                25.4350, 81.8460, "comfortable, modern amenities, swimming pool, fitness center, relaxing"));


        for (EntityLocationModel model : locationModels) {
            model.setCategory(LocationCategories.HOTEL.name());
            Log.e("ghat","hotel: "+model.getTitle());
            dataManager.insert(model);
        }

    }

    private void saveParkingLocations() {
        List<EntityLocationModel> locationModels = new ArrayList<>();

        locationModels.add(new EntityLocationModel(29, "Triveni Sangam Car Parking",
                "This parking facility is situated close to the Triveni Sangam, providing convenient access for visitors aiming to reach the confluence point.",
                 25.4297,  81.8853, "parking, Triveni Sangam, convenient access, confluence"));

        locationModels.add(new EntityLocationModel(30, "Shiv Baba Parking",
                "Designated for vehicles arriving from Ayodhya and Pratapgarh, this parking area allows visitors to proceed via Sangam Lower Marg to enter the Mela region.",
                 25.4350,  81.8460, "parking, Ayodhya, Pratapgarh, Sangam Lower Marg, Mela region"));

        locationModels.add(new EntityLocationModel(31, "Saraswati Parking",
                "Allocated for vehicles coming from Varanasi, this parking spot requires visitors to walk through Chatnagar Road to enter the Mela area.",
                 25.4350, 81.8460, "parking, Varanasi, Chatnagar Road, Mela area"));

        locationModels.add(new EntityLocationModel(32, "Chini Mill Parking",
                "Intended for vehicles arriving from Jaunpur, with visitors proceeding via Old GT Road to access the Mela region.",
                25.4350, 81.8460, "parking, Jaunpur, Old GT Road, Mela region"));

        locationModels.add(new EntityLocationModel(33, "Kali Extension Plot No. 17 Parking",
                "Designated for vehicles from Kanpur and Kausambi, with visitors accessing the Mela via GT Jawahar Chauraha and Kali Marg.",
                 25.4350, 81.8460, "parking, Kanpur, Kausambi, GT Jawahar Chauraha, Kali Marg, Mela region"));


        for (EntityLocationModel model : locationModels) {
            model.setCategory(LocationCategories.PARKING.name());
            Log.e("ghat","parking: "+model.getTitle());
            dataManager.insert(model);
        }


    }


}