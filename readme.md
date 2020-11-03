# USER LOGIN APPLICATION

This application provides endpoints to perform CRUD operations on users.

Architectural Analysis:

I used Dependency Injection and annotations like "auto wired" and Interfaces in my application to make it loosely coupled.
Loosely coupled applications gives us the flexibility to modify single pieces of code without disturbing other code. Loosely coupled architectures can also allow for more independent scaling. For example, in a loosely coupled network, engineers could work on improving the capacity or performance of one node with less effect on the other nodes in the system. As the workflows are less dependent, they can be scaled or adjusted individually. This can be considered as implementing “horizontal scaling” in the application for load management.

As load increases on the application, it is important to have both high availability and horizontal
scalability.

As load increases, caches can help significantly with offloading resources. Caching brings performance improvement for the client-side and better scope for scalability for a server because the load has reduced.

We can use caching to cache web API methods that are relatively stale. For eg, every time we hit the GET request, the response would be the same list of users that are present in the database. So instead of hitting the database every single time to get the list of users, we can use a cache to store the response from the get request. We can refresh the cache whenever a new user is added or any update is made on the database.

If we have a global traffic, we can consider implementing a CDN to help take some of the load off of your
origin server and place it on the CDN's edge servers for even faster performance.

Having a Layered system:
REST APIs allows to use a layered system architecture where you deploy the APIs on server A, and store data
on server B and authenticate requests in Server C, for example. A client cannot ordinarily tell whether it
is connected directly to the end server or an intermediary along the way. This way we can decrease the load
on the servers by not doing all the operations on a single server.
