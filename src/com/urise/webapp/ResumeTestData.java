package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = createResume("uuid1", "Григорий Кислин");
        System.out.println(resume.getFullName());
        EnumSet.allOf(ContactType.class).forEach(contactType -> System.out.println(contactType.getTitle() + " " + resume.getContact(contactType)));
        EnumSet.allOf(SectionType.class).forEach(sectionType -> System.out.println("\n" + sectionType.getTitle() + " " + resume.getSection(sectionType)));
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        List<String> achievements = new ArrayList<>();
        List<String> qualifications = new ArrayList<>();
        List<Organization> experiences = new ArrayList<>();
        List<Organization> education = new ArrayList<>();

        qualifications.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный " +
                "maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                "Более 1000 выпускников.");
        qualifications.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        qualifications.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, " +
                "Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера");
        qualifications.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, " +
                "GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга");
        qualifications.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии " +
                "через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга " +
                "системы по JMX (Jython/ Django).");
        qualifications.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, " +
                "Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа");

        achievements.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        achievements.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        achievements.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        achievements.add("MySQL, SQLite, MS SQL, HSQLDB");
        achievements.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        achievements.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        achievements.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), " +
                "Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        achievements.add("Python: Django.");
        achievements.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        achievements.add("JScala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        achievements.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, " +
                "SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, " +
                "BPMN2, LDAP, OAuth1, OAuth2, JWT");
        achievements.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        achievements.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        achievements.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        achievements.add("Родной русский, английский \"upper intermediate\"");

        List<Organization.Position> positions1 = new ArrayList<>();
        positions1.add(new Organization.Position(2013, Month.OCTOBER, null, "Создание, " +
                "организация и проведение Java онлайн проектов и стажировок."));
        experiences.add(new Organization("Java Online Projects", "http://javaops.ru/", positions1));

        List<Organization.Position> positions2 = new ArrayList<>();
        positions2.add(new Organization.Position(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                        "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация " +
                        "по OAuth1, OAuth2, JWT SSO."));
        experiences.add(new Organization("Wrike",
                "https://www.wrike.com/", positions2));

        List<Organization.Position> positions3 = new ArrayList<>();
        positions3.add(new Organization.Position(2012, Month.APRIL, 2014, Month.OCTOBER, "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                        "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование " +
                        "системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка " +
                        "интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, " +
                        "экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                        "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, " +
                        "Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote " +
                        "scripting via ssh tunnels, PL/Python"));
        experiences.add(new Organization("RIT Center", "", positions3));

        List<Organization.Position> positions4 = new ArrayList<>();
        positions4.add(new Organization.Position(2010, Month.DECEMBER, 2012, Month.APRIL, "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT," +
                        " GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения " +
                        "для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга." +
                        " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        experiences.add(new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", positions4));

        List<Organization.Position> positions5 = new ArrayList<>();
        positions5.add(new Organization.Position(2008, Month.JUNE, 2010, Month.DECEMBER, "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                        "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX " +
                        "клиента (Python/ Jython, Django, ExtJS)"));
        experiences.add(new Organization("Yota",
                "https://www.yota.ru/", positions5));

        List<Organization.Position> positions6 = new ArrayList<>();
        positions6.add(new Organization.Position(2007, Month.MARCH, 2008, Month.JUNE, "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) " +
                        "частей кластерного J2EE приложения (OLAP, Data mining)"));
        experiences.add(new Organization("Enkata", "http://enkata.com/", positions6));

        List<Organization.Position> positions7 = new ArrayList<>();
        positions7.add(new Organization.Position(2005, Month.JANUARY, 2007, Month.FEBRUARY, "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО" +
                        " на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        experiences.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html", positions7));

        List<Organization.Position> positions8 = new ArrayList<>();
        positions8.add(new Organization.Position(1997, Month.SEPTEMBER, 2005, Month.JANUARY, "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        experiences.add(new Organization("Alcatel", "https://www.siemens.com/ru/ru/home.html", positions8));

        List<Organization.Position> positions9 = new ArrayList<>();
        positions9.add(new Organization.Position(2013, Month.MARCH, 2013, Month.MAY, null, "\"Functional Programming Principles in Scala\" by Martin Odersky"));
        education.add(new Organization("Coursera", "https://www.coursera.org/course/progfun", positions9));

        List<Organization.Position> positions10 = new ArrayList<>();
        positions10.add(new Organization.Position(2011, Month.MARCH, 2011, Month.APRIL, null, "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""));
        education.add(new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", positions10));

        List<Organization.Position> positions11 = new ArrayList<>();
        positions11.add(new Organization.Position(2005, Month.JANUARY, 2005, Month.APRIL, null, "3 месяца обучения мобильным IN сетям (Берлин)"));
        education.add(new Organization("Siemens AG", "http://www.siemens.ru/", positions11));

        List<Organization.Position> positions12 = new ArrayList<>();
        positions12.add(new Organization.Position(1997, Month.SEPTEMBER, 1998, Month.MARCH, null, "6 месяцев обучения цифровым телефонным сетям (Москва)"));
        education.add(new Organization("Alcatel", "http://www.alcatel.ru/", positions12));

        List<Organization.Position> positions13 = new ArrayList<>();
        positions13.add(new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирантура (программист С, С++)", null));
        positions13.add(new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.JULY, "Инженер (программист Fortran, C)", null));

        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/", positions13));

        List<Organization.Position> positions14 = new ArrayList<>();
        positions14.add(new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE, null, "Закончил с отличием"));
        education.add(new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", positions14));

        resume.addContact(ContactType.MOBILE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(qualifications));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(experiences));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(education));
        return resume;
    }
}
