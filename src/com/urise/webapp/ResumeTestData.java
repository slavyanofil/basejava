package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Григорий Кислин");
        List<String> achievements = new ArrayList<>();
        List<String> qualifications = new ArrayList<>();
        List<Experience> experiences = new ArrayList<>();
        List<Experience> education = new ArrayList<>();

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

        experiences.add(new Experience(LocalDate.of(2013, 10, 1), LocalDate.now(), "Java Online Projects",
                "http://javaops.ru/", "Автор проекта\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experiences.add(new Experience(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1), "Wrike",
                "https://www.wrike.com/", "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация " +
                "по OAuth1, OAuth2, JWT SSO."));
        experiences.add(new Experience(LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1), "RIT Center",
                "", "Java архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование " +
                "системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка " +
                "интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, " +
                "экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, " +
                "Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote " +
                "scripting via ssh tunnels, PL/Python"));
        experiences.add(new Experience(LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1), "Luxoft (Deutsche Bank)",
                "http://www.luxoft.ru/", "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT," +
                " GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения " +
                "для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга." +
                " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        experiences.add(new Experience(LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1), "Yota",
                "https://www.yota.ru/", "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX " +
                "клиента (Python/ Jython, Django, ExtJS)"));
        experiences.add(new Experience(LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1), "Enkata",
                "http://enkata.com/", "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) " +
                "частей кластерного J2EE приложения (OLAP, Data mining)"));
        experiences.add(new Experience(LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1), "Siemens AG",
                "https://www.siemens.com/ru/ru/home.html", "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО" +
                " на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        experiences.add(new Experience(LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1), "Alcatel",
                "https://www.siemens.com/ru/ru/home.html", "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));

        education.add(new Experience(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1), "Coursera",
                "https://www.coursera.org/course/progfun", "\"Functional Programming Principles in Scala\" by Martin Odersky"));
        education.add(new Experience(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), "Luxoft",
                "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""));
        education.add(new Experience(LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), "Siemens AG",
                "http://www.siemens.ru/", "3 месяца обучения мобильным IN сетям (Берлин)"));
        education.add(new Experience(LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), "Alcatel",
                "http://www.alcatel.ru/", "6 месяцев обучения цифровым телефонным сетям (Москва)"));
        education.add(new Experience(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/", "Аспирантура (программист С, С++)"));
        education.add(new Experience(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), "",
                "", "Инженер (программист Fortran, C)"));
        education.add(new Experience(LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1), "Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru/", "Закончил с отличием"));

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
        resume.addSection(SectionType.ACHIEVEMENT, new BulletedListSection(achievements));
        resume.addSection(SectionType.QUALIFICATIONS, new BulletedListSection(qualifications));
        resume.addSection(SectionType.EXPERIENCE, new Organization(experiences));
        resume.addSection(SectionType.EDUCATION, new Organization(education));

        System.out.println(resume.getFullName());
        EnumSet.allOf(ContactType.class).forEach(contactType -> System.out.println(contactType.getTitle() + " " + resume.getContact(contactType)));
        EnumSet.allOf(SectionType.class).forEach(sectionType -> System.out.println("\n" + sectionType.getTitle() + " " + resume.getSection(sectionType)));
    }
}
