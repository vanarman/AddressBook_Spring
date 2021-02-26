#  AddressBook SYSC4806

This repository represents is part of the practice LAB to investigate and tried on practice how CI/CD stack works. The live example can be found [here](https://address-book-spring-sysc4806.herokuapp.com/buddyInfo).

# API Overview

*NOTE: All buddy described in the following section must be in a JSON format.*

## AddressBook:

* Get all AddressBooks

    **Link**: ```{ URL }/api/addressBook```

    **Request method**: GET

* Get AddressBook by ID

    **Link**: ```{ URL }/api/addressBook/{address book id}```

    **Request method**: GET

* Add AddressBook

    **Link**: ```{ URL }/api/addressBook```

    **Request method**: POST
    
    **Body**: 
    ```JSON
    {}
    ```

* Add Buddy to AddressBook

    **Link**: ```{ URL }/api/addressBook/{address book id}/addBuddy```

    **Request method**: POST

    **Body**: 
    ```JSON 
    {
        "name": "John",
        "phoneNumber": "+1 (613) 349-10-74",
        "address": "1 Colonel By Dr"
    }
                        OR
    {
        "name": "Merry",
        "phoneNumber": "+1 (613) 349-10-74",
    }
    ```

* Remove AddressBook

    **Link**: ```{ URL }/api/addressBook/{address book id}```

    **Request method**: DELETE

## Buddy Info

* Get all Buddies

    **Link**: ```{ URL }/api/buddyInfo```

    **Request method**: GET

* Get Buddy Information by ID

    **Link**: ```{ URL }/api/buddyInfo/{buddy id}```

    **Request method**: GET

* Add Buddy

    **Link**: ```{ URL }/api/buddyInfo```

    **Request method**: POST
    
    **Body**: 
    ```JSON
    {
        "name": "John",
        "phoneNumber": "+1 (613) 349-10-74",
        "address": "1 Colonel By Dr"
    }
                        OR
    {
        "name": "Merry",
        "phoneNumber": "+1 (613) 349-10-74",
    }
    ```

* Update/Add Buddy

    **Link**: ```{ URL }/api/buddyInfo/{buddy id}```

    **Request method**: PUT

    **Body**: 
    ```JSON
    {
        "name": "John",
        "phoneNumber": "+1 (613) 349-10-74",
        "address": "1 Colonel By Dr"
    }
                        OR
    {
        "name": "Merry",
        "phoneNumber": "+1 (613) 349-10-74",
    }
    ```

* Remove Buddy

    **Link**: ```{ URL }/api/buddyInfo/{buddy id}```

    **Request method**: DELETE