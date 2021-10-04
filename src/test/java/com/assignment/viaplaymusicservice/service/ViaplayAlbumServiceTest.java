package com.assignment.viaplaymusicservice.service;

import com.assignment.viaplaymusicservice.client.CoverArtClient;
import com.assignment.viaplaymusicservice.client.DiscogClient;
import com.assignment.viaplaymusicservice.client.MusicBrainzClient;
import com.assignment.viaplaymusicservice.dto.ArtistDetailsResponse;
import com.assignment.viaplaymusicservice.dto.CoverArtResponse;
import com.assignment.viaplaymusicservice.dto.DiscogResponse;
import com.assignment.viaplaymusicservice.dto.MusicBrainzResponse;
import com.assignment.viaplaymusicservice.exception.ViaplayServiceDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static com.assignment.viaplaymusicservice.TestUtil.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class ViaplayAlbumServiceTest {

    private ViaplayAlbumService viaplayAlbumService;

    @MockBean
    private MusicBrainzClient musicBrainzClient;
    @MockBean
    private DiscogClient discogClient;
    @MockBean
    private CoverArtClient coverArtClient;

    @BeforeEach
    public void setup() {
        viaplayAlbumService = new ViaplayAlbumService(musicBrainzClient, discogClient, coverArtClient, "discog");
    }

    @Test
    public void testGetArtistDetailsResponse_givenMbId_returnsArtistDetailsResponse() throws IOException {

        String mbId = "test mbId";
        MusicBrainzResponse musicBrainzResponse = buildMusicBrainzResponse();
        CoverArtResponse coverArtResponse = buildCoverArtResponse();
        DiscogResponse discogResponse = buildDiscogResponse();

        ArtistDetailsResponse artistDetailsResponse = buildArtistDetailsResponse(mbId, discogResponse);

        when(musicBrainzClient.getArtistDetails(anyString())).thenReturn(musicBrainzResponse);
        when(coverArtClient.getCoverArtResponse(anyString())).thenReturn(coverArtResponse);
        when(discogClient.getDiscogResponse(anyString())).thenReturn(discogResponse);

        ArtistDetailsResponse actualResponse = viaplayAlbumService.getArtistDetailsResponse(mbId);

        assertEquals(artistDetailsResponse, actualResponse);
        verify(musicBrainzClient, atLeastOnce()).getArtistDetails(anyString());
        verify(coverArtClient, times(musicBrainzResponse.getReleaseGroups().size())).getCoverArtResponse(anyString());
        verify(discogClient, atLeastOnce()).getDiscogResponse(anyString());
    }


    @Test
    public void testGetArtistDetailsResponse_givenIncorrectMbId_throwsException() {
        Exception exception = assertThrows(ViaplayServiceDataException.class, () -> {
            viaplayAlbumService.getArtistDetailsResponse("Incorrect-mbId");
        });

        String expectedMessage = "Music brainz response is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

}