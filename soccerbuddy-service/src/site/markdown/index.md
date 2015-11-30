## Overview

A set of RESTful web services that help users interact with the system to perform various activities. These services abstract away the interaction with the databases through
their interfaces.

Here's the definitive list of the service APIs involved:

#### Registration

Helps users:
  
  * register (subscribe)
  * un-register (un-subscribe)
  * re-register (re-subscribe)
    
into the application. Read more about it [here](registration-service.html). 

#### Profile

TODO

## Request validation

The payloads are usually validated through the use of `@Valid` annotation with the respective model/payload objects expressing their validity (usually through
the use of annotations). Operations failing the request validation will respond with a HTTP status code of `400`, the failed payload and the reason(s) for failure
after being handled by an exception handler that also logs the failure(s) to the log files.

## Aspects and Advices

The operations are usually subject to being advised by custom `Spring AOP` advice classes. The aspects that are usually addressed include:

  * logging
  * profiling
  * exception handling (other than the validation errors)
  
The main `Aspect` classes are:

 * `com.soccerbuddy.service.ServiceMethodHandler`