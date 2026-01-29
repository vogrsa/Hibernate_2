package com.javarush;

import com.javarush.dao.*;
import com.javarush.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.time.LocalDateTime;
import java.util.Properties;


public class Main {
    private final SessionFactory sessionFactory;

    private final ActorDao actorDao;
    private final AddressDao addressDao;
    private final CategoryDao categoryDao;
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private final CustomerDao customerDao;
    private final FilmDao filmDao;
    private final FilmTextDao filmTextDao;
    private final InventoryDao inventoryDao;
    private final LanguageDao languageDao;
    private final PaymentDao paymentDao;
    private final RentalDao rentalDao;
    private final StaffDao staffDao;
    private final StoreDao storeDao;



    public Main() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "shfW/root25SQL234");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
//        properties.put(Environment.SHOW_SQL, "true");

        sessionFactory = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addProperties(properties)
                .buildSessionFactory();

        actorDao = new ActorDao(sessionFactory);
        addressDao = new AddressDao(sessionFactory);
        categoryDao = new CategoryDao(sessionFactory);
        cityDao = new CityDao(sessionFactory);
        countryDao = new CountryDao(sessionFactory);
        customerDao = new CustomerDao(sessionFactory);
        filmDao = new FilmDao(sessionFactory);
        filmTextDao = new FilmTextDao(sessionFactory);
        inventoryDao = new InventoryDao(sessionFactory);
        languageDao = new LanguageDao(sessionFactory);
        paymentDao = new PaymentDao(sessionFactory);
        rentalDao = new RentalDao(sessionFactory);
        staffDao = new StaffDao(sessionFactory);
        storeDao = new StoreDao(sessionFactory);


    }

    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();
        //main.customerReturnInventoryToStore();
        main.customerRentalInventory(customer);

    }

    private void customerRentalInventory(Customer customer) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Film film = filmDao.getAnyFilmForRent();
            Store store = storeDao.getItems(0, 1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            session.getTransaction().commit();
        }
    }

    private void customerReturnInventoryToStore() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Rental rental = rentalDao.getAnyUnreternedRental();
            rental.setReturnDate(LocalDateTime.now());
            rentalDao.save(rental);
            session.getTransaction().commit();
        }
    }

    private Customer createCustomer() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Store store = storeDao.getItems(0, 1).get(0);
            City city = cityDao.getByName("Araatuba");

            Address address = new Address();
            address.setAddress(" Frunze str., 1");
            address.setPhone("495-796-8538");
            address.setCity(city);
            address.setDistrict("New Podlipki");
            addressDao.save(address);

            Customer customer = new Customer();
            customer.setActive(true);
            customer.setAddress(address);
            customer.setEmail("test@gmail.com");
            customer.setStore(store);
            customer.setFirstName("Vitya");
            customer.setLastName("Jezlov");
            customerDao.save(customer);

            session.getTransaction().commit();
            return customer;
        }
    }


}