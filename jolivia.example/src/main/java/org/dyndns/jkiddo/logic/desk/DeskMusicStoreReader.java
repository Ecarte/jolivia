/*******************************************************************************
 * Copyright (c) 2013 Jens Kristian Villadsen.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Jens Kristian Villadsen - Lead developer, owner and creator
 ******************************************************************************/
package org.dyndns.jkiddo.logic.desk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.metadata.XMPDM;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.dyndns.jkiddo.dmap.chunks.audio.AlbumArtist;
import org.dyndns.jkiddo.dmap.chunks.audio.SongAlbum;
import org.dyndns.jkiddo.dmap.chunks.audio.SongAlbumId;
import org.dyndns.jkiddo.dmap.chunks.audio.SongArtist;
import org.dyndns.jkiddo.dmap.chunks.audio.SongArtistId;
import org.dyndns.jkiddo.dmap.chunks.audio.SongArtworkCount;
import org.dyndns.jkiddo.dmap.chunks.audio.SongBitrate;
import org.dyndns.jkiddo.dmap.chunks.audio.SongCodecType;
import org.dyndns.jkiddo.dmap.chunks.audio.SongDataKind;
import org.dyndns.jkiddo.dmap.chunks.audio.SongDateAdded;
import org.dyndns.jkiddo.dmap.chunks.audio.SongDateModified;
import org.dyndns.jkiddo.dmap.chunks.audio.SongDescription;
import org.dyndns.jkiddo.dmap.chunks.audio.SongExtraData;
import org.dyndns.jkiddo.dmap.chunks.audio.SongFormat;
import org.dyndns.jkiddo.dmap.chunks.audio.SongGenre;
import org.dyndns.jkiddo.dmap.chunks.audio.SongSampleRate;
import org.dyndns.jkiddo.dmap.chunks.audio.SongSize;
import org.dyndns.jkiddo.dmap.chunks.audio.SongTime;
import org.dyndns.jkiddo.dmap.chunks.audio.SortAlbum;
import org.dyndns.jkiddo.dmap.chunks.audio.SortAlbumArtist;
import org.dyndns.jkiddo.dmap.chunks.audio.SortArtist;
import org.dyndns.jkiddo.dmap.chunks.audio.SortName;
import org.dyndns.jkiddo.dmap.chunks.audio.extension.ExtendedMediaKind;
import org.dyndns.jkiddo.dmap.chunks.audio.extension.MediaKind;
import org.dyndns.jkiddo.dmp.chunks.media.ContainerItemId;
import org.dyndns.jkiddo.dmp.chunks.media.ItemId;
import org.dyndns.jkiddo.dmp.chunks.media.ItemKind;
import org.dyndns.jkiddo.dmp.chunks.media.ItemName;
import org.dyndns.jkiddo.dmp.chunks.media.Listing;
import org.dyndns.jkiddo.dmp.chunks.media.ListingItem;
import org.dyndns.jkiddo.dmp.chunks.media.PersistentId;
import org.dyndns.jkiddo.dmp.model.MediaItem;
import org.dyndns.jkiddo.logic.interfaces.IMusicStoreReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class DeskMusicStoreReader implements IMusicStoreReader
{
	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DeskMusicStoreReader.class);
	private Collection<MediaItem> mapOfSongToFile;
	private final File path;
	private final Parser parser;

	public DeskMusicStoreReader(final String path)
	{
		parser = new AutoDetectParser();
		this.mapOfSongToFile = Lists.newArrayList();
		this.path = new File(path);
	} 

	public DeskMusicStoreReader(final File path)
	{
		parser = new AutoDetectParser();
		this.mapOfSongToFile = Lists.newArrayList();
		this.path = path;
	}

	public DeskMusicStoreReader()
	{
		this(System.getProperty("user.dir") + System.getProperty("file.separator") + "etc");
		this.mapOfSongToFile = Lists.newArrayList();
	}

	@Override
	public Collection<MediaItem> readTunes()
	{
		try
		{
			traverseRootPathRecursively(path);
		}
		catch(final Exception e)
		{
			LOGGER.info(e.getMessage(), e);
		}
		return mapOfSongToFile;
	}

	protected void traverseRootPathRecursively(final File f) throws FileNotFoundException, IOException, InterruptedException, SAXException, TikaException
	{
		if(f.isDirectory())
		{
			final File[] contents = f.listFiles();
			if(contents != null)
			{
				for (final File content : contents) {
					traverseRootPathRecursively(content);
				}
			}
			else
				LOGGER.debug("Symlink'ish ... " + f.getAbsolutePath());
		}
		else if(isMusic(f))
		{
			mapOfSongToFile.add(populateSong(f));
		}
	}

	private MediaItem populateSong(final File file) throws IOException, SAXException, TikaException
	{
		final BodyContentHandler handler = new BodyContentHandler();
		final Metadata metadata = new Metadata();
		final InputStream content = new FileInputStream(file);
		parser.parse(content, handler, metadata, new ParseContext());

		final MediaItem song = new MediaItem();
		song.setItemName(metadata.get(TikaCoreProperties.TITLE));
		if(Strings.isNullOrEmpty(metadata.get(TikaCoreProperties.TITLE)))
		{
			song.setItemName(file.getName());
		}
		// song.setGenre(metadata.get(XMPDM.GENRE));
		try
		{
			// song.setTrackNumber(metadata.getInt(XMPDM.TRACK_NUMBER));
		}
		catch(final Exception e)
		{
			LOGGER.debug(e.getMessage(), e);
		}
		song.setSongAlbum(metadata.get(XMPDM.ALBUM));
		song.setSongArtist(metadata.get(XMPDM.ARTIST));
		// song.setComposer(metadata.get(XMPDM.COMPOSER));
		// song.setSize(file.length());
		try
		{
			song.setSongTime((int) Double.parseDouble(metadata.get(XMPDM.DURATION)));
		}
		catch(final Exception e)
		{
			LOGGER.debug(e.getMessage(), e);
		}
		try
		{
			final Calendar c = Calendar.getInstance();
			c.setTime(metadata.getDate(XMPDM.SHOT_DATE));
			// song.setYear(c.get(Calendar.YEAR));
		}
		catch(final Exception e)
		{
			LOGGER.debug(e.getMessage(), e);
		}
		song.setExternalIdentifer(file.getAbsolutePath());
		return song;
	}
	private static boolean isMusic(final File f)
	{
		if(f.getPath().endsWith(".mp3") || f.getPath().endsWith(".wav"))
			return true;
		return false;
	}

	@Override
	public URI getTune(final String tune) throws Exception
	{
		return new File(tune).toURI();
	}
	/*
	 * class MusicItem implements IMusicItem { private String artist; private String album; private String title; private long duration; public String getTitle() { return title; }
	 * @Override public String getArtist() { return artist; }
	 * @Override public String getAlbum() { return album; }
	 * @Override public void setArtist(String artist) { this.artist = artist; }
	 * @Override public void setAlbum(String album) { this.album = album; }
	 * @Override public void setTitle(String title) { this.title = title; }
	 * @Override public void setComposer(String composer) { // TODO Auto-generated method stub }
	 * @Override public void setGenre(String genre) { // TODO Auto-generated method stub }
	 * @Override public void setTrackNumber(int trackNumber) { // TODO Auto-generated method stub }
	 * @Override public void setYear(int Year) { // TODO Auto-generated method stub }
	 * @Override public void setSize(long size) { // TODO Auto-generated method stub }
	 * @Override public long getSize() { // TODO Auto-generated method stub return 0; }
	 * @Override public long getDuration() { return duration; }
	 * @Override public void setDuration(long value) { duration = value; } }
	 */

	@Override
	public void readTunesMemoryOptimized(final Listing listing, final Map<Long, String> map) throws Exception
	{
		
		final Collection<MediaItem> songs = readTunes();
		System.gc();
		final AtomicLong id = new AtomicLong(1);

		for(final MediaItem song : songs)
		{
			final ListingItem item = new ListingItem();
			item.add(new ItemKind(ItemKind.AUDIO));
			item.add(new SongDataKind(SongDataKind.UNKNOWN));
			item.add(new SongArtist(song.getSongArtist()));
			item.add(new SongArtistId(666));
			item.add(new SongSize(0x430000));
			item.add(new SongTime(1000));
			item.add(new SongDateAdded(403958));
			item.add(new ItemName(song.getItemName()));
			item.add(new SongGenre("Other"));
			item.add(new ContainerItemId(id.get()));
			item.add(new ItemId(id.get()));
			item.add(new SongSampleRate(SongSampleRate.KHZ_44100));
			item.add(new SongDescription("MPEG audio file"));
			item.add(new SongAlbum(song.getSongAlbum()));
			item.add(new SongAlbumId(123456));
			item.add(new SongBitrate(0x80));
			item.add(new ExtendedMediaKind(ExtendedMediaKind.UNKNOWN_ONE));
			item.add(new AlbumArtist("Unknown"));
			item.add(new SortArtist("WonderWall"));
			
			item.add(new SongDateModified(123456));
			item.add(new SortName("Unknown"));
			item.add(new SongCodecType(SongCodecType.MPEG));
			item.add(new PersistentId(id.get()));
			item.add(new MediaKind(MediaKind.KIND_1));
			item.add(new SortAlbum(""));
			item.add(new SongFormat(SongFormat.MP3));
			item.add(new SortAlbumArtist("unknown"));
			item.add(new SongExtraData(1));
			item.add(new SongArtworkCount(1));
			
			listing.add(item);
			map.put(Long.valueOf(id.getAndIncrement()), song.getExternalIdentifer());
		}
		songs.clear();
		
	}
}
