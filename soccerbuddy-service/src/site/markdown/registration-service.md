## Introduction

A RESTful web service that exposes methods a client can use to handle entity (users, groups, etc.) registration activities. The `interface`
that qualifies all such registration implementations is the `com.soccerbuddy.service.registration.RegistrationService<R>` (`<R>` is the type of 
the registering entity). At this time, registrable entities include:

  * User
  * Group

## Characteristics

All the operations:

  * Accept and produce `JSON`
  * Accept a request body containing the entity involved
  * Validate the entity through the use of `@Valid` (see [here](index.html#Request_Validation))
  * Throw a `com.soccerbuddy.data.DataStoreException` if a problem occurred during a `CRUD` operation on the data store
  * Are subject to `AOP` advices listed [here](index.html#Aspects_And_Advices)
  * Produce a response body containing the result of the execution of the entity

unless otherwise specified.

## API

Here's a list of activities a client can perform, the classes they are implemented on, the URLs the APIs are exposed on and other relevant information:

<table>
  <tr>
    <th>Entity</th>
    <th>Activity</th>
    <th>Controller class</th>
    <th>Operation</th>
    <th>URI pattern</th>
    <th>HTTP verb</th>
  </tr>
  
  <tr>
    <td rowspan="3">User</td>
    <td>Registration</td>
    <td>com.soccerbuddy.service.registration.UserRegistrationService</td>
    <td>register</td>
    <td>/register/user</td>
    <td>PUT</td>
  </tr>
  
  <tr>
    <td>Un-registration</td>
    <td>com.soccerbuddy.service.registration.UserRegistrationService</td>
    <td>unregister</td>
    <td>/register/user</td>
    <td>DELETE</td>
  </tr>
  
  <tr>
    <td>Re-registration</td>
    <td>com.soccerbuddy.service.registration.UserRegistrationService</td>
    <td>reregister</td>
    <td>/register/user</td>
    <td>POST</td>
  </tr>
  
  <tr>
    <td rowspan="3">Group</td>
    <td>Registration</td>
    <td>com.soccerbuddy.service.registration.GroupRegistrationService</td>
    <td>register</td>
    <td>/register/group</td>
    <td>PUT</td>
  </tr>
  
  <tr>
    <td>Un-registration</td>
    <td>com.soccerbuddy.service.registration.GroupRegistrationService</td>
    <td>unregister</td>
    <td>/register/group</td>
    <td>DELETE</td>
  </tr>
  
  <tr>
    <td>Re-registration</td>
    <td>com.soccerbuddy.service.registration.GroupRegistrationService</td>
    <td>reregister</td>
    <td>/register/group</td>
    <td>POST</td>
  </tr>
</table>
