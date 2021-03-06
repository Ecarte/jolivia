package org.dyndns.jkiddo.dmcp.chunks.media.extension;

import org.dyndns.jkiddo.dmp.chunks.StringChunk;

import org.dyndns.jkiddo.dmp.IDmapProtocolDefinition.DmapChunkDefinition;
import org.dyndns.jkiddo.dmp.DMAPAnnotation;

@DMAPAnnotation(type=DmapChunkDefinition.ceQr)
public class PlayQueueArtist extends StringChunk  {
	public PlayQueueArtist() {
		this("");
	}

	public PlayQueueArtist(String i) {
		super("ceQr", "com.apple.itunes.playqueue-artist", i);
	}
}
