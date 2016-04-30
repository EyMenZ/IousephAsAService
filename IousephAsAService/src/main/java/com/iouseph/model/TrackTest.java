package com.iouseph.model;

import static org.junit.Assert.*;

import org.junit.*;

public class TrackTest {

	@Test
	public void testTrack() {
		Track track = new Track();
		assert(track != null);
		assert(track.getAlbum() == null);
		assert(track.getArtist() == null);
		assert(track.getDuration() == 0);
		assert(track.getExternalUrl() == null);
		assert(track.getId() == null);
		assert(track.getImage() == null);
		assert(track.getSource() == null);
		assert(track.getTitle() == null);
	}

	@Test
	public void testGetId() {
		Track track = new Track("1", "", "", 0, "", "", "", "");
		assert(track.getId() == "1");
	}

	@Test
	public void testSetId() {
		Track track = new Track();
		track.setId("id");
		assert(track.getId() == "id");
	}

	@Test
	public void testGetTitle() {
		Track track = new Track("", "title", "", 0, "", "", "", "");
		assertSame(track.getTitle(), "title");
	}

	@Test
	public void testSetTitle() {
		Track track = new Track();
		track.setTitle("title");
		assertSame(track.getTitle(), "title");
	}

	@Test
	public void testGetExternalUrl() {
		Track track = new Track("", "", "http://externalurl", 0, "", "", "", "");
		assertSame(track.getExternalUrl(), "http://externalurl");
	}

	@Test
	public void testSetExternalUrl() {
		Track track = new Track();
		track.setExternalUrl("http://externalurl");
		assertSame(track.getExternalUrl(), "http://externalurl");
	}

	@Test
	public void testGetDuration() {
		Track track = new Track("", "", "", 42, "", "", "", "");
		assert(track.getDuration() == 42);
	}

	@Test
	public void testSetDuration() {
		Track track = new Track();
		track.setDuration(42);
		assert(track.getDuration() == 42);
	}

	@Test
	public void testGetArtist() {
		Track track = new Track("", "", "", 0, "Artist", "", "", "");
		assertSame(track.getArtist(), "Artist");
	}

	@Test
	public void testSetArtist() {
		Track track = new Track();
		track.setArtist("Artist");
		assertSame(track.getArtist(), "Artist");
	}

	@Test
	public void testGetAlbum() {
		Track track = new Track("", "", "", 0, "", "Album", "", "");
		assertSame(track.getAlbum(), "Album");
	}

	@Test
	public void testSetAlbum() {
		Track track = new Track();
		track.setAlbum("Album");
		assertSame(track.getAlbum(), "Album");
	}

	@Test
	public void testGetImage() {
		Track track = new Track("", "", "", 0, "", "", "Image", "");
		assertSame(track.getImage(), "Image");
	}

	@Test
	public void testSetImage() {
		Track track = new Track();
		track.setImage("Image");
		assertSame(track.getImage(), "Image");
	}

	@Test
	public void testGetSource() {
		Track track = new Track("", "", "", 0, "", "", "", "http://source");
		assertSame(track.getSource(), "http://source");
	}

	@Test
	public void testSetSource() {
		Track track = new Track();
		track.setSource("http://source");
		assertSame(track.getSource(), "http://source");
	}

}
