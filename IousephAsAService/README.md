# IousephAsAService

## API

### /track/:search

search for a song

_request_

__GET__ /track/search=requested song

_response_

```json
{[
    {
        "title": "",
        "id": "",
        "artist": "",
        "album": "",
        "source": "",
        "externalUrl": "",
        "image": "",
        "source": ""
    },
    {}
]}
```


### /users/

retourne un utilisateur selon un id

_request_

__GET__ /users/userid

_response_

```json
{
    "id": "",
    "password": "",
    "username": "",
    "playlist": ""
}
```

### /playlists/userid

retourne la liste des playlists d'un utilisateur

_request_

__GET__ /playlists/userid

_response_

```json
{[
    {
        "id": "",
        "idUser": "",
        "owner": "",
        "title": "",
        "source": "",
        "externalUrl": "",
        "tracks": [{
                    "title": "",
                    "id": "",
                    "artist": "",
                    "album": "",
                    "externalUrl": "",
                    "image": "",
                    "source": ""
                    },
                    {}
                   ]
    },
    {}
]}
```

### /playlist/userid/playlistid

retourne la playlist playlistid de l'utilisateur userid

_request_

__GET__ /playlist/userid/playlistid

_response_

```json
{
    "id": "",
    "idUser": "",
    "owner": "",
    "title": "",
    "source": "",
    "externalUrl": "",
    "tracks": [{
                "title": "",
                "id": "",
                "artist": "",
                "album": "",
                "externalUrl": "",
                "image": "",
                "source": ""
                },
                {}
                ]
}
```

### /playlist/delete/userid/playlistid

requete pour supprimer la playlist playlistid du user userid

__GET__ /playlist/delete/userid/playlistid


_response_

```
PlaylistDeleted
PlaylistNotDeleted
```

### /playlist/edit/userid/playlistid/newtitle

changer le nom de la playlist playlistid du user userid

_request_

__GET__ /playlist/edit/userid/playlistid/newtitle


_response_

```
PlaylistNameChanged
PlaylistNameNotChanged
```

### /playlist/delete/userid/playlistid/trackid

retirer le track trackid de la playlist playlistid qui appartien a l'utilisateur userid

_request_

__GET__ /playlist/delete/userid/playlistid/trackid


_response_

```
TrackDeleted
TrackNotDeleted
```

### /playlist/addtrack

ajouter une track a une playlist d'un utilisateur

_request_

__POST__ /playlist/addtrack

body:

```json
{
    "user_id": "",
    "playlist_id": "",
    "id": "",
    "album": "",
    "artist": "",
    "externalUrl": "",
    "image": "",
    "source": "",
    "title": ""
}
```

_response_

```
TrackAdded
TrackNotAdded
```

### /playlist/create

creer une playlist

_request_

__POST__ /playlist/create

body:

```json
{
    "userId": "",
    "title": ""
}
```

_response_

```
PlaylistAdded
PlaylistNotAdded
```


### /login

authentification

_request_

__POST__ /login

body:

```json
{
    "username": "",
    "pwd": ""
}
```

_response_

```json
{
    "id": "",
    "password": "",
    "username": "",
    "playlists": ""
}
```

