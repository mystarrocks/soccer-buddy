#### [Bootstrap themes from Bootswatch][themes]

[![Bootswatch themes](images/carousel-themes.png)][themes]

Select a free theme for your website from an excellent gallery at [Bootswatch][bootswatch].
Out of the box support for these and other custom [Bootstrap][bootstrap] themes.


#### [Page layouts][reflow-layouts]

[![Page layouts](images/carousel-layouts.jpg)][reflow-layouts]

Write plain text in Markdown or APT, then set different layouts to your page sections.


#### [Modern skin][reflow-misc]

[![Modern skin](images/carousel-components.jpg)][reflow-misc]

Reflow skin uses modern components from Bootstrap and other libraries, upgrades Maven generated
site and provides further enhancements.


#### [New Velocity tools][reflow-tools]

[![Reflow Velocity tools](images/carousel-tools.png)][reflow-tools]

The skin adds a library of new Velocity tools to use in your own Maven template: rewrite HTML code,
support per-page configurations and more!


[bootswatch]: http://bootswatch.com
[bootstrap]: http://getbootstrap.com
[themes]: skin/themes/
[reflow-layouts]: skin/layouts.html
[reflow-misc]: skin/misc.html
[reflow-tools]: reflow-velocity-tools/


---


## About

<span class="color-highlight">soccer-buddy</span> is suite of a mobile application plus its
service APIs that allow users to register themselves with the system, organize themselves
into groups, setup game times and well, play their heart out!

## Libraries and Tools

Here are some of the common libraries/tools the project uses:

  * Java SE 8
  * Apache Maven 3.x
  * Spring Boot 1.2.7 (+ several other Spring libraries)
  * Google datastore v1beta2-rev1-3.0.2
  * Project Lombok 1.16.6
  * Guava 18.0
  * JUnit 4.12
  * Mockito 1.10.19
  * Powermock 1.6.3
  * SLF4J 1.7.12 + Log4J 2.4.1

## Developer Guide

### Getting Started

1. Download and setup

    * Java SE 8 | [Download](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
  
    * STS Eclipse | [Download](https://spring.io/tools/sts/all)
  
    * Lombok | [Download](https://projectlombok.org/)
  
    * Posh GIT | [Download](http://dahlbyk.github.io/posh-git/)


2. Clone the repository: https://github.com/mystarrocks/soccer-buddy.git

3. Import `soccer-buddy` modules inside the repository into your STS


[soccerbuddy-model]: soccerbuddy-model/index.html
[soccerbuddy-data]: soccerbuddy-data/index.html
[soccerbuddy-service]: soccerbuddy-service/index.html

### Project Organization

The project is organized into the following modules:

  * <span class="color-highlight">soccerbuddy-parent</span>
    The Maven parent and aggregator project that composes the modules mentioned below. Mainly used to configure properties that are common
    to all the modules and compose them into a reactor for easily building and deploying.
    
  * <span class="color-highlight">soccerbuddy-model</span>
    Contains all the model and `ORM` classes. The `soccerbuddy-service` and `soccerbuddy-data` modules depend on this module
    as a result.
    
    [Read more &raquo;][soccerbuddy-model]
    
  * <span class="color-highlight">soccerbuddy-data</span>
    Contains all the data source utility classes that help the `soccerbuddy-service` interact with the data store (the persistent database).
    
    [Read more &raquo;][soccerbuddy-data]
    
  * <span class="color-highlight">soccerbuddy-service</span>
    Contains all the web service interfaces that enable a client (a mobile application, say) to interact with the system and perform `CRUD`
    operations on the entities in the system.
    
    [Read more &raquo;][soccerbuddy-service]