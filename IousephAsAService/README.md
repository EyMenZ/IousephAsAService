# IousephAsAService

## API

### /track

search for a song

_request_

__GET__ /track?search=<requested song>

_response_

```json
{[
]}
```

### /playlists

get all playlists

_request_

__GET__ /playlists

_response_

```json
{[]}
```

### /playlist

get a specific playlist

_request_

__GET__ /playlist?id=<id of the playlist>

_response_

```json
{}
```

### /playlist

update a playlist, add a song, modify name, etc...

__POST__ /playlist

body:

```json
{}
```

_response_

### /create_playlist

create a new playlist

_request_

__POST__ /create_playlist

body:

```json
{}
```

_response_

### /login

login as a registered user

_request_

__POST__ /login

body:

```json
{
    "username": "...",
    "password": "..."
}
```

_response_

### /sign_in

create a new user

_request_

__POST__ /sign_in

body:

```json
{
    "username": "...",
    "password": "..."
}
```

_response_

