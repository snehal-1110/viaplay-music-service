# Viaplay Music Service
## Rest API to retrieve information from 3rd party client.

This is a REST API which  provides the clients with information about a specific music artist.
The information is collected from 3 different sources:
- MusicBrainz 
- Cover Art Archive
- Discog


## Features

- Create Rest API which uses JSON message format
- API takes a MBID (MusicBrainz Identifier) and return a result containing required information from Discog and Cover Art Archive
- Displays list of all albums the artist has released from MusicBrainz
- Displays links to album cover art corresponding to the albums of the required artist from Cover Art Archive.
- Displays description of the artist fetched from Discog which is given as input from MusicBrainz response (Discog does not support MBIDs hence we extract the discog id from url given in the MusicBrainz response.


## Tech

- [Spring Boot] 
- [Swagger ui] -  User Interface for endpoints testing or interaction.
- [Postman]- Endpoints testing or interaction.

## Installation

- Java 11
- Maven build tool version 3.8.2 to build and run.
  Install the dependencies and devDependencies and start the server.


```sh
cd viaplay-music-service
mvn clean install
mvn spring-boot:run
```
Once the application starts locally, Rest API endpoint to get the Artist release album information is available on [http://localhost:8080/swagger-ui.html] or through postman using [http://localhost:8080/api/album/{mbid}]

Test Data {MBID}
- f27ec8db-af05-4f36-916e-3d57f91ecf5e
- d5da1841-9bc8-4813-9f89-11098090148e
- bf24ca37-25f4-4e34-9aec-460b94364cfc


## Sample Response
{
"mbid": "f27ec8db-af05-4f36-916e-3d57f91ecf5e",
"description": "American singer, dancer, entertainer, songwriter, producer and recording artist.\r\n\r\nBorn: 29 August 1958 in Gary, Indiana, USA.\r\nDied: 25 June 2009 in Los Angeles, California, USA (aged 50).\r\n\r\nKnown affectionately as the \"King Of Pop\", Jackson was a singer, dancer, musician, music producer, writer, entertainer, singer-songwriter, choreographer, record producer, recording artist, poet, arranger, businessman, philanthropist, actor, voice artist, and comedian.\r\n \r\nJackson began his career as the youngest member of [a=The Jackson 5] and started his solo recording career in 1971. Brother of recording artists [a=Jackie Jackson], [a=Janet Jackson], [a=Jermaine Jackson], [a=La Toya Jackson], [a=Marlon Jackson], [a=Randy Jackson], [a=Rebbie Jackson] & [a=Tito Jackson], as well as uncle of [a=3T].\r\n\r\nInducted into Rock And Roll Hall of Fame in 2001 (as performer).\r\n\r\nOn June 25, 2009, Michael Jackson died of acute propofol and benzodiazepine intoxication at his home on North Carolwood Drive in the Holmby Hills neighborhood of Los Angeles, CA. His personal physician, Conrad Murray, said he had found Jackson in his room, not breathing and with a barely detectable pulse, and that he administered CPR on Jackson to no avail. After a call was placed to 9-1-1 at 12:21 p.m., Jackson was treated by paramedics at the scene and was later pronounced dead at the Ronald Reagan UCLA Medical Center.",
"albums": [
{
"title": "Got to Be There",
"id": "97e0014d-a267-33a0-a868-bb4e2552918a",
"image": "http://coverartarchive.org/release/7d65853b-d547-4885-86a6-51df4005768c/1619682960.jpg"
},
{
"title": "Ben",
"id": "51343255-0ad3-3635-9aa2-548ba939b23e",
"image": "http://coverartarchive.org/release/cf81f5db-6b4d-493b-8f8f-c0f8c51442f9/11670488852.jpg"
},
{
"title": "Music & Me",
"id": "06b064b9-01e7-32d8-b585-86404584e795",
"image": "http://coverartarchive.org/release/7c73f72d-8fa2-45a7-9125-a04696f64f3a/1620517729.jpg"
},
{
"title": "Forever, Michael",
"id": "50b9d7de-9124-33c0-83a3-76722bf346e5",
"image": "http://coverartarchive.org/release/3fdd7c32-2da8-480c-8b70-1c628a7fd009/1619702784.jpg"
},
{
"title": "Off the Wall",
"id": "ee749c63-5699-38e0-b565-7e84414648d9",
"image": "http://coverartarchive.org/release/6258e39d-bef4-4d5a-a654-440cf4c4c29a/5349015874.jpg"
},
{
"title": "Thriller",
"id": "f32fab67-77dd-3937-addc-9062e28e4c37",
"image": "http://coverartarchive.org/release/e1b94ba6-c63c-4c2d-8928-9d1a525b7000/22018478497.jpg"
},
{
"title": "Bad",
"id": "a5711a77-42d1-3f4c-830c-e27a96f0800f",
"image": "http://coverartarchive.org/release/60b529f1-f99b-499f-9b3d-e96f9971039e/17067123637.jpg"
},
{
"title": "Dangerous",
"id": "d6b52521-0dfa-390f-970f-790174c22752",
"image": "http://coverartarchive.org/release/45417dcf-d97a-4f36-b729-441846bda882/8294209818.jpg"
},
{
"title": "HIStory: Past, Present and Future, Book I",
"id": "2324e560-e8ba-302d-a43d-2ea5ec9c83f7",
"image": "http://coverartarchive.org/release/69b4db43-519d-4621-a72f-30f8d30c2158/8316263548.jpg"
},
{
"title": "Invincible",
"id": "c24c5313-da47-3155-8277-a6a1a4237966",
"image": "http://coverartarchive.org/release/17fb437a-437f-3153-8bc6-3e6135032d03/1613909539.jpg"
},
{
"title": "The Best of Michael Jackson",
"id": "90915175-cc35-3970-99f5-8f279ab59585",
"image": "http://coverartarchive.org/release/56274aa3-457e-49b6-ad61-46df84cef737/18821116150.jpg"
},
{
"title": "One Day in Your Life",
"id": "0621bd78-b867-39ab-8606-9636bfd94447",
"image": "http://coverartarchive.org/release/264039fe-1253-4aa5-baae-406163c733b5/1395290002.jpg"
},
{
"title": "14 Greatest Hits With the Jackson 5",
"id": "22fc8d45-6802-46f8-8a78-6ae823a9ed92",
"image": "http://coverartarchive.org/release/f1c43fd5-066d-462a-93fa-33d7bb23564d/1613886666.jpg"
},
{
"title": "18 Greatest Hits",
"id": "ffc3f8b5-7b22-40ed-8867-0cad52b6b2ae",
"image": "http://coverartarchive.org/release/75276adf-7504-4bba-8630-631ef020c31b/1871938266.jpg"
},
{
"title": "18 Greatest Hits",
"id": "6f33ff5d-025a-42d6-827e-6d5bb5a30b4a",
"image": "http://coverartarchive.org/release/6402fbc9-cb27-4306-96eb-10e6dc489aaf/12592766269.jpg"
},
{
"title": "Great Songs and Performances That Inspired the Motown 25th Anniversary Television Special",
"id": "e6696f23-a356-4cff-a096-fdf2a1e1a358",
"image": "http://coverartarchive.org/release/d77fe3d8-8a4b-4849-87d3-dabfb9f75e44/1619672416.jpg"
},
{
"title": "Farewell My Summer Love",
"id": "500d9b05-68c3-3535-86e3-cf685869efc0",
"image": "http://coverartarchive.org/release/8172928a-a6c7-4d7c-83c8-5db2a4575094/13404446094.jpg"
},
{
"title": "Got to Be There / Ben",
"id": "b513c135-b957-305b-9c5c-7f829d6195b3",
"image": "http://coverartarchive.org/release/f20ad013-43c9-4f91-9a23-741f5c6cff6a/15444575030.jpg"
},
{
"title": "Anthology",
"id": "37906983-1005-36fb-b8e7-3a04687e6f4f",
"image": "http://coverartarchive.org/release/a7a74484-8c25-47e3-9afc-7de701ad3dde/1619836290.jpg"
},
{
"title": "The 12″ Tape",
"id": "6a427340-2d07-45b5-b557-aaaab91357fd",
"image": "http://coverartarchive.org/release/9d647919-ce5e-46ea-92bd-5d87c5b82012/28872180093.jpg"
},
{
"title": "Love Songs",
"id": "5baedc41-91da-44c9-8289-6619a853e635",
"image": "http://coverartarchive.org/release/f91ef9f3-f203-4951-af9a-4e1fa20a1f0d/1619530618.jpg"
},
{
"title": "The Michael Jackson Mix",
"id": "60005e6f-2299-3a22-a928-e8002b91e834",
"image": "http://coverartarchive.org/release/f9ede3a4-95e3-44a2-8502-53b8992fdf70/1620171628.jpg"
},
{
"title": "Instrumental Version Collection",
"id": "f674b393-2e2a-3008-aca3-2f5a115ebe31",
"image": "http://coverartarchive.org/release/044a3fdc-b591-4776-acef-bb3dc70b70cb/1619551610.jpg"
},
{
"title": "The Original Soul of Michael Jackson",
"id": "30abac22-0c88-3340-b955-61dd3be73c55",
"image": "http://coverartarchive.org/release/7aad4ef7-06e9-4a6e-8ae2-205a568c7ca6/1513880900.jpg"
},
{
"title": "The Motown Years…His Greatest Hits",
"id": "1516d16c-fad9-413f-99ba-ad1f767c608a",
"image": "http://coverartarchive.org/release/1abad59d-5f52-4b16-9b48-e8beeaf76ec8/8554032338.jpg"
}
]
}