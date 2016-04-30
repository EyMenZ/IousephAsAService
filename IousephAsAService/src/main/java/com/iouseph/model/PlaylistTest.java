package com.iouseph.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PlaylistTest {

	@Test
	public void testPlaylist() {
		Playlist playlist = new Playlist();
		assertNotNull(playlist);
		Playlist playlist2 = new Playlist("", "", "", "", null, "");
		assertNotNull(playlist2);
	}

	@Test
	public void testGetTracks() {
		ArrayList<Track> l = new ArrayList<Track>();
		Track t = new Track();
		l.add(t);
		Playlist playlist = new Playlist("", "", "", "", l, "");
		assertNotNull(playlist.getTracks());
		assert(playlist.getTracks()== l);
	}

	@Test
	public void testAddTrack() {
		ArrayList<Track> l = new ArrayList<Track>();
		Track t = new Track();
		l.add(t);
		Playlist playlist = new Playlist("", "", "", "", l, "");
		assertTrue(playlist.addTrack(t));
		
		assertFalse(playlist.addTrack(t));
	}

	@Test
	public void testDeleteTrackTrack() {
		ArrayList<Track> l = new ArrayList<Track>();
		Track t = new Track();
		l.add(t);
		Playlist playlist = new Playlist("", "", "", "", l, "");
		assertTrue(playlist.deleteTrack(t));
		assertFalse(playlist.deleteTrack(t));
		
	}

	@Test
	public void testClearPlaylist() {
		ArrayList<Track> l = new ArrayList<Track>();
		Track t = new Track();
		l.add(t);
		Playlist playlist = new Playlist("", "", "", "", l, "");
		playlist.deleteTrack(t);
		playlist.clearPlaylist();
		assert(playlist.getTracks().size() == 0);
	}

	@Test
	public void testGetTitle() {

		Playlist playlist = new Playlist("", "title", "", "", null, "");
		assertSame(playlist.getTitle(), "title");

	}

	@Test
	public void testSetTitle() {
		Playlist playlist = new Playlist();
		playlist.setTitle("title");
		assertSame(playlist.getTitle(), "title");
		
	}

	@Test
	public void testGetOwner() {
		Playlist playlist = new Playlist("", "", "owner", "", null, "");
		assertSame(playlist.getOwner(), "owner");
	}

	@Test
	public void testSetOwner() {
		Playlist playlist = new Playlist();
		playlist.setOwner("owner");
		assertSame(playlist.getOwner(), "owner");
	}

	@Test
	public void testGetSource() {
		Playlist playlist = new Playlist("", "", "", "source", null, "");
		assertSame(playlist.getSource(), "source");
	}

	@Test
	public void testSetSource() {
		Playlist playlist = new Playlist();
		playlist.setSource("source");
		assertSame(playlist.getSource(), "source");
	}

	@Test
	public void testGetId() {
		Playlist playlist = new Playlist("id", "", "", "", null, "");
		assertSame(playlist.getId(), "id");
	}

	@Test
	public void testSetId() {
		Playlist playlist = new Playlist();
		playlist.setId("id");
		assertSame(playlist.getId(), "id");
	}

	@Test
	public void testSetTracks() {
		Playlist playlist = new Playlist("id", "", "", "", null, "");
		ArrayList<Track> l = new ArrayList<Track>();
		Track t = new Track();
		l.add(t);
		playlist.setTracks(l);
		assert(playlist.getTracks() == l);
	}

	@Test
	public void testGetUrl() {
		Playlist playlist = new Playlist("", "", "", "", null, "url");
		assertSame(playlist.getUrl(), "url");
	}

	@Test
	public void testSetUrl() {
		Playlist playlist = new Playlist();
		playlist.setUrl("url");
		assertSame(playlist.getUrl(), "url");
	}

	@Test
	public void testGetUser() {
	}

	@Test
	public void testSetUser() {
	}

	@Test
	public void testGetIdUser() {
	}

	@Test
	public void testSetIdUser() {
	}

}
