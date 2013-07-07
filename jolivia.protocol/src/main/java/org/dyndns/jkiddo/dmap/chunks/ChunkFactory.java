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
/*
 * Digital Audio Access Protocol (DAAP) Library
 * Copyright (C) 2004-2010 Roger Kapsi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dyndns.jkiddo.dmap.chunks;

import java.util.HashMap;
import java.util.Map;

import org.dyndns.jkiddo.dmap.DmapUtil;

public final class ChunkFactory
{

	private final Map<Integer, Class<? extends Chunk>> map = new HashMap<Integer, Class<? extends Chunk>>();

	public ChunkFactory()
	{
		map.put(new Integer(0x61654373), org.dyndns.jkiddo.dmap.chunks.audio.ArtworkChecksum.class); // aeCs
		map.put(new Integer(0x6D736175), org.dyndns.jkiddo.dmap.chunks.media.AuthenticationMethod.class); // msau
		map.put(new Integer(0x6D736173), org.dyndns.jkiddo.dmap.chunks.media.AuthenticationSchemes.class); // msas
		map.put(new Integer(0x6D62636C), org.dyndns.jkiddo.dmap.chunks.media.Bag.class); // mbcl
		map.put(new Integer(0x6162706C), org.dyndns.jkiddo.dmap.chunks.audio.BaseContainer.class); // abpl
		map.put(new Integer(0x6162616C), org.dyndns.jkiddo.dmap.chunks.audio.BrowseAlbumListing.class); // abal
		map.put(new Integer(0x61626172), org.dyndns.jkiddo.dmap.chunks.audio.BrowseArtistListing.class); // abar
		map.put(new Integer(0x61626370), org.dyndns.jkiddo.dmap.chunks.audio.BrowseComposerListing.class); // abcp
		map.put(new Integer(0x6162676E), org.dyndns.jkiddo.dmap.chunks.audio.BrowseGenreListing.class); // abgn
		map.put(new Integer(0x6D636F6E), org.dyndns.jkiddo.dmap.chunks.media.Container.class); // mcon
		map.put(new Integer(0x6D637463), org.dyndns.jkiddo.dmap.chunks.media.ContainerCount.class); // mctc
		map.put(new Integer(0x6D637469), org.dyndns.jkiddo.dmap.chunks.media.ContainerItemId.class); // mcti
		map.put(new Integer(0x6D636E61), org.dyndns.jkiddo.dmap.chunks.media.ContentCodesName.class); // mcna
		map.put(new Integer(0x6D636E6D), org.dyndns.jkiddo.dmap.chunks.media.ContentCodesNumber.class); // mcnm
		map.put(new Integer(0x6D636372), org.dyndns.jkiddo.dmap.chunks.media.ContentCodesResponse.class); // mccr
		map.put(new Integer(0x6D637479), org.dyndns.jkiddo.dmap.chunks.media.ContentCodesType.class); // mcty
		map.put(new Integer(0x6170726F), org.dyndns.jkiddo.dmap.chunks.audio.DaapProtocolVersion.class); // apro
		map.put(new Integer(0x6162726F), org.dyndns.jkiddo.dmap.chunks.audio.DatabaseBrowse.class); // abro
		map.put(new Integer(0x6D736463), org.dyndns.jkiddo.dmap.chunks.media.DatabaseCount.class); // msdc
		map.put(new Integer(0x61706C79), org.dyndns.jkiddo.dmap.chunks.audio.DatabaseContainerns.class); // aply
		map.put(new Integer(0x6D64626B), org.dyndns.jkiddo.dmap.chunks.media.DatabaseShareType.class); // mdbk
		map.put(new Integer(0x61646273), org.dyndns.jkiddo.dmap.chunks.audio.DatabaseItems.class); // adbs
		map.put(new Integer(0x6D75646C), org.dyndns.jkiddo.dmap.chunks.media.DeletedIdListing.class); // mudl
		map.put(new Integer(0x6D64636C), org.dyndns.jkiddo.dmap.chunks.media.Dictionary.class); // mdcl
		map.put(new Integer(0x6D70726F), org.dyndns.jkiddo.dmap.chunks.media.DmapProtocolVersion.class); // mpro
		map.put(new Integer(0x6D656473), org.dyndns.jkiddo.dmap.chunks.media.EditCommandSupported.class); // meds
		map.put(new Integer(0x61654D6B), org.dyndns.jkiddo.dmap.chunks.audio.EMediaKind.class); // aeMk
		map.put(new Integer(0x668D6368), org.dyndns.jkiddo.dmap.chunks.unknown.HasChildContainers.class); // f?ch
		map.put(new Integer(0x61654856), org.dyndns.jkiddo.dmap.chunks.audio.HasVideo.class); // aeHV
		map.put(new Integer(0x6D696D63), org.dyndns.jkiddo.dmap.chunks.media.ItemCount.class); // mimc
		map.put(new Integer(0x6D696964), org.dyndns.jkiddo.dmap.chunks.media.ItemId.class); // miid
		map.put(new Integer(0x6D696B64), org.dyndns.jkiddo.dmap.chunks.media.ItemKind.class); // mikd
		map.put(new Integer(0x6D696E6D), org.dyndns.jkiddo.dmap.chunks.media.ItemName.class); // minm
		map.put(new Integer(0x61654149), org.dyndns.jkiddo.dmap.chunks.audio.ITMSArtistId.class); // aeAI
		map.put(new Integer(0x61654349), org.dyndns.jkiddo.dmap.chunks.audio.ITMSComposerId.class); // aeCI
		map.put(new Integer(0x61654749), org.dyndns.jkiddo.dmap.chunks.audio.ITMSGenreId.class); // aeGI
		map.put(new Integer(0x61655049), org.dyndns.jkiddo.dmap.chunks.audio.ITMSPlaylistId.class); // aePI
		map.put(new Integer(0x61655349), org.dyndns.jkiddo.dmap.chunks.audio.ITMSSongId.class); // aeSI
		map.put(new Integer(0x61655346), org.dyndns.jkiddo.dmap.chunks.audio.ITMSStorefrontId.class); // aeSF
		map.put(new Integer(0x6D6C636C), org.dyndns.jkiddo.dmap.chunks.media.Listing.class); // mlcl
		map.put(new Integer(0x6D6C6974), org.dyndns.jkiddo.dmap.chunks.media.ListingItem.class); // mlit
		map.put(new Integer(0x6D736C72), org.dyndns.jkiddo.dmap.chunks.media.LoginRequired.class); // mslr
		map.put(new Integer(0x6D6C6F67), org.dyndns.jkiddo.dmap.chunks.media.LoginResponse.class); // mlog
		map.put(new Integer(0x61654D4B), org.dyndns.jkiddo.dmap.chunks.audio.MediaKind.class); // aeMK
		map.put(new Integer(0x61655356), org.dyndns.jkiddo.dmap.chunks.audio.MusicSharingVersion.class); // aeSV
		map.put(new Integer(0x61654E56), org.dyndns.jkiddo.dmap.chunks.audio.NormVolume.class); // aeNV
		map.put(new Integer(0x6D70636F), org.dyndns.jkiddo.dmap.chunks.media.ParentContainerId.class); // mpco
		map.put(new Integer(0x6D706572), org.dyndns.jkiddo.dmap.chunks.media.PersistentId.class); // mper
		map.put(new Integer(0x6170726D), org.dyndns.jkiddo.dmap.chunks.audio.PlaylistRepeatMode.class); // aprm
		map.put(new Integer(0x6170736D), org.dyndns.jkiddo.dmap.chunks.audio.PlaylistShuffleMode.class); // apsm
		map.put(new Integer(0x6170736F), org.dyndns.jkiddo.dmap.chunks.audio.ItemsContainer.class); // apso
		map.put(new Integer(0x61655043), org.dyndns.jkiddo.dmap.chunks.audio.Podcast.class); // aePC
		map.put(new Integer(0x61655050), org.dyndns.jkiddo.dmap.chunks.audio.PodcastPlaylist.class); // aePP
		map.put(new Integer(0x636D766F), org.dyndns.jkiddo.dmap.chunks.control.RelativeVolume.class); // cmvo
		map.put(new Integer(0x6D727072), org.dyndns.jkiddo.dmap.chunks.media.RemotePersistentID.class); // mrpr
		map.put(new Integer(0x61727376), org.dyndns.jkiddo.dmap.chunks.audio.Resolve.class); // arsv
		map.put(new Integer(0x61726966), org.dyndns.jkiddo.dmap.chunks.audio.ResolveInfo.class); // arif
		map.put(new Integer(0x6D72636F), org.dyndns.jkiddo.dmap.chunks.media.ReturnedCount.class); // mrco
		map.put(new Integer(0x61766462), org.dyndns.jkiddo.dmap.chunks.audio.ServerDatabases.class); // avdb
		map.put(new Integer(0x6D737276), org.dyndns.jkiddo.dmap.chunks.media.ServerInfoResponse.class); // msrv
		map.put(new Integer(0x6D757372), org.dyndns.jkiddo.dmap.chunks.media.ServerRevision.class); // musr
		map.put(new Integer(0x6D6C6964), org.dyndns.jkiddo.dmap.chunks.media.SessionId.class); // mlid
		map.put(new Integer(0x61655350), org.dyndns.jkiddo.dmap.chunks.audio.SmartPlaylist.class); // aeSP
		map.put(new Integer(0x6173616C), org.dyndns.jkiddo.dmap.chunks.audio.SongAlbum.class); // asal
		map.put(new Integer(0x61736172), org.dyndns.jkiddo.dmap.chunks.audio.SongArtist.class); // asar
		map.put(new Integer(0x61736274), org.dyndns.jkiddo.dmap.chunks.audio.SongBeatsPerMinute.class); // asbt
		map.put(new Integer(0x61736272), org.dyndns.jkiddo.dmap.chunks.audio.SongBitrate.class); // asbr
		map.put(new Integer(0x61736374), org.dyndns.jkiddo.dmap.chunks.audio.SongCategory.class); // asct
		map.put(new Integer(0x61736373), org.dyndns.jkiddo.dmap.chunks.audio.SongCodecSubtype.class); // ascs
		map.put(new Integer(0x61736364), org.dyndns.jkiddo.dmap.chunks.audio.SongCodecType.class); // ascd
		map.put(new Integer(0x6173636D), org.dyndns.jkiddo.dmap.chunks.audio.SongComment.class); // ascm
		map.put(new Integer(0x6173636F), org.dyndns.jkiddo.dmap.chunks.audio.SongCompilation.class); // asco
		map.put(new Integer(0x61736370), org.dyndns.jkiddo.dmap.chunks.audio.SongComposer.class); // ascp
		map.put(new Integer(0x6173636E), org.dyndns.jkiddo.dmap.chunks.audio.SongContentDescription.class); // ascn
		map.put(new Integer(0x61736372), org.dyndns.jkiddo.dmap.chunks.audio.SongContentRating.class); // ascr
		map.put(new Integer(0x6173646B), org.dyndns.jkiddo.dmap.chunks.audio.SongDataKind.class); // asdk
		map.put(new Integer(0x6173756C), org.dyndns.jkiddo.dmap.chunks.audio.SongDataUrl.class); // asul
		map.put(new Integer(0x61736461), org.dyndns.jkiddo.dmap.chunks.audio.SongDateAdded.class); // asda
		map.put(new Integer(0x6173646D), org.dyndns.jkiddo.dmap.chunks.audio.SongDateModified.class); // asdm
		map.put(new Integer(0x61736474), org.dyndns.jkiddo.dmap.chunks.audio.SongDescription.class); // asdt
		map.put(new Integer(0x61736462), org.dyndns.jkiddo.dmap.chunks.audio.SongDisabled.class); // asdb
		map.put(new Integer(0x61736463), org.dyndns.jkiddo.dmap.chunks.audio.SongDiscCount.class); // asdc
		map.put(new Integer(0x6173646E), org.dyndns.jkiddo.dmap.chunks.audio.SongDiscNumber.class); // asdn
		map.put(new Integer(0x61736571), org.dyndns.jkiddo.dmap.chunks.audio.SongEqPreset.class); // aseq
		map.put(new Integer(0x6173666D), org.dyndns.jkiddo.dmap.chunks.audio.SongFormat.class); // asfm
		map.put(new Integer(0x6173676E), org.dyndns.jkiddo.dmap.chunks.audio.SongGenre.class); // asgn
		map.put(new Integer(0x61677270), org.dyndns.jkiddo.dmap.chunks.audio.SongGrouping.class); // agrp
		map.put(new Integer(0x61736B79), org.dyndns.jkiddo.dmap.chunks.audio.SongKeywords.class); // asky
		map.put(new Integer(0x61736C63), org.dyndns.jkiddo.dmap.chunks.audio.SongLongDescription.class); // aslc
		map.put(new Integer(0x61737276), org.dyndns.jkiddo.dmap.chunks.audio.SongRelativeVolume.class); // asrv
		map.put(new Integer(0x61737372), org.dyndns.jkiddo.dmap.chunks.audio.SongSampleRate.class); // assr
		map.put(new Integer(0x6173737A), org.dyndns.jkiddo.dmap.chunks.audio.SongSize.class); // assz
		map.put(new Integer(0x61737374), org.dyndns.jkiddo.dmap.chunks.audio.SongStartTime.class); // asst
		map.put(new Integer(0x61737370), org.dyndns.jkiddo.dmap.chunks.audio.SongStopTime.class); // assp
		map.put(new Integer(0x6173746D), org.dyndns.jkiddo.dmap.chunks.audio.SongTime.class); // astm
		map.put(new Integer(0x61737463), org.dyndns.jkiddo.dmap.chunks.audio.SongTrackCount.class); // astc
		map.put(new Integer(0x6173746E), org.dyndns.jkiddo.dmap.chunks.audio.SongTrackNumber.class); // astn
		map.put(new Integer(0x61737572), org.dyndns.jkiddo.dmap.chunks.audio.SongUserRating.class); // asur
		map.put(new Integer(0x61737972), org.dyndns.jkiddo.dmap.chunks.audio.SongYear.class); // asyr
		map.put(new Integer(0x63616961), org.dyndns.jkiddo.dmap.chunks.control.SpeakerActive.class); // caia
		map.put(new Integer(0x63617370), org.dyndns.jkiddo.dmap.chunks.control.SpeakerList.class); // casp
		map.put(new Integer(0x61655053), org.dyndns.jkiddo.dmap.chunks.audio.SpecialPlaylist.class); // aePS
		map.put(new Integer(0x6D74636F), org.dyndns.jkiddo.dmap.chunks.media.SpecifiedTotalCount.class); // mtco
		map.put(new Integer(0x6D737474), org.dyndns.jkiddo.dmap.chunks.media.Status.class); // mstt
		map.put(new Integer(0x6D737473), org.dyndns.jkiddo.dmap.chunks.media.StatusString.class); // msts
		map.put(new Integer(0x6D73616C), org.dyndns.jkiddo.dmap.chunks.media.SupportsAutoLogout.class); // msal
		map.put(new Integer(0x6D736272), org.dyndns.jkiddo.dmap.chunks.media.SupportsBrowse.class); // msbr
		map.put(new Integer(0x6D736578), org.dyndns.jkiddo.dmap.chunks.media.SupportsExtensions.class); // msex
		map.put(new Integer(0x6D736978), org.dyndns.jkiddo.dmap.chunks.media.SupportsIndex.class); // msix
		map.put(new Integer(0x6D737069), org.dyndns.jkiddo.dmap.chunks.media.SupportsPersistentIds.class); // mspi
		map.put(new Integer(0x6D737179), org.dyndns.jkiddo.dmap.chunks.media.SupportsQuery.class); // msqy
		map.put(new Integer(0x6D737273), org.dyndns.jkiddo.dmap.chunks.media.SupportsResolve.class); // msrs
		map.put(new Integer(0x6D737570), org.dyndns.jkiddo.dmap.chunks.media.SupportsUpdate.class); // msup
		map.put(new Integer(0x6D73746D), org.dyndns.jkiddo.dmap.chunks.media.TimeoutInterval.class); // mstm
		map.put(new Integer(0x636D6774), org.dyndns.jkiddo.dmap.chunks.control.UnknownGT.class); // cmgt
		map.put(new Integer(0x6165494D), org.dyndns.jkiddo.dmap.chunks.audio.UnknownIM.class); // aeIM
		map.put(new Integer(0x6D736D61), org.dyndns.jkiddo.dmap.chunks.media.SpeakerMacAddress.class); // msma
		map.put(new Integer(0x6165524D), org.dyndns.jkiddo.dmap.chunks.audio.UnknownRM.class); // aeRM
		map.put(new Integer(0x63617664), org.dyndns.jkiddo.dmap.chunks.control.UnknownVD.class); // cavd
		map.put(new Integer(0x6D757064), org.dyndns.jkiddo.dmap.chunks.media.UpdateResponse.class); // mupd
		map.put(new Integer(0x6D757479), org.dyndns.jkiddo.dmap.chunks.media.UpdateType.class); // muty
		map.put(new Integer(0x636D7374), org.dyndns.jkiddo.dmap.chunks.control.PlayingStatus.class); // cmst
		map.put(new Integer(0x636D7372), org.dyndns.jkiddo.dmap.chunks.control.StatusRevision.class); // cmsr
		map.put(new Integer(0x63617073), org.dyndns.jkiddo.dmap.chunks.control.PlayStatus.class); // caps
		map.put(new Integer(0x63617368), org.dyndns.jkiddo.dmap.chunks.control.ShuffleStatus.class); // cash
		map.put(new Integer(0x63617270), org.dyndns.jkiddo.dmap.chunks.control.RepeatStatus.class); // carp
		map.put(new Integer(0x63616673), org.dyndns.jkiddo.dmap.chunks.control.FullscreenStatus.class); // cafs
		map.put(new Integer(0x63617673), org.dyndns.jkiddo.dmap.chunks.control.VisualizerStatus.class); // cavs
		map.put(new Integer(0x63617663), org.dyndns.jkiddo.dmap.chunks.control.UnknownVC.class); // cavc
		map.put(new Integer(0x63616173), org.dyndns.jkiddo.dmap.chunks.control.UnknownAS.class); // caas
		map.put(new Integer(0x63616172), org.dyndns.jkiddo.dmap.chunks.control.UnknownAR.class); // caar
		map.put(new Integer(0x63616665), org.dyndns.jkiddo.dmap.chunks.control.UnknownFE.class); // cafe
		map.put(new Integer(0x63617665), org.dyndns.jkiddo.dmap.chunks.control.UnknownVE.class); // cave
		map.put(new Integer(0x63617375), org.dyndns.jkiddo.dmap.chunks.control.UnknownSU.class); // casu
		map.put(new Integer(0x63655175), org.dyndns.jkiddo.dmap.chunks.control.UnknownQU.class); // ceQu
		map.put(new Integer(0x63616e70), org.dyndns.jkiddo.dmap.chunks.control.NowPlaying.class); // canp
		map.put(new Integer(0x63616e6e), org.dyndns.jkiddo.dmap.chunks.control.TrackName.class); // cann
		map.put(new Integer(0x63616e61), org.dyndns.jkiddo.dmap.chunks.control.TrackArtist.class); // cana
		map.put(new Integer(0x63616e6c), org.dyndns.jkiddo.dmap.chunks.control.TrackAlbum.class); // canl
		map.put(new Integer(0x63616e67), org.dyndns.jkiddo.dmap.chunks.control.TrackGenre.class); // cang
		map.put(new Integer(0x61736169), org.dyndns.jkiddo.dmap.chunks.audio.SongAlbumId.class); // asai
		map.put(new Integer(0x636d6d6b), org.dyndns.jkiddo.dmap.chunks.control.UnknownMK.class); // cmmk
		map.put(new Integer(0x61654773), org.dyndns.jkiddo.dmap.chunks.audio.GeniusSeed.class); // aeGs
		map.put(new Integer(0x63654753), org.dyndns.jkiddo.dmap.chunks.control.GeniusSelectable.class); // ceGs
		map.put(new Integer(0x63617361), org.dyndns.jkiddo.dmap.chunks.control.UnknownSA.class); // casa
		map.put(new Integer(0x63616e74), org.dyndns.jkiddo.dmap.chunks.control.ProgressRemain.class); // cant
		map.put(new Integer(0x63617374), org.dyndns.jkiddo.dmap.chunks.control.ProgressTotal.class); // cant
		map.put(new Integer(0x6d73686c), org.dyndns.jkiddo.dmap.chunks.media.UnknownHL.class); // mshl
		map.put(new Integer(0x6d736863), org.dyndns.jkiddo.dmap.chunks.media.UnknownHC.class); // mshc
		map.put(new Integer(0x6d736869), org.dyndns.jkiddo.dmap.chunks.media.UnknownHI.class); // mshi
		map.put(new Integer(0x6d73686e), org.dyndns.jkiddo.dmap.chunks.media.UnknownHN.class); // mshn
		map.put(new Integer(0x6167616c), org.dyndns.jkiddo.dmap.chunks.audio.AlbumSearchContainer.class); // agal
		map.put(new Integer(0x61736161), org.dyndns.jkiddo.dmap.chunks.AlbumArtist.class); // asaa
		map.put(new Integer(0x61746564), org.dyndns.jkiddo.dmap.chunks.audio.SupportsExtraData.class); // ated
		map.put(new Integer(0x61736772), org.dyndns.jkiddo.dmap.chunks.audio.SupportsGroups.class); // asgr
		map.put(new Integer(0x61737365), org.dyndns.jkiddo.dmap.chunks.audio.UnknownSE.class); // asse
		map.put(new Integer(0x61654d51), org.dyndns.jkiddo.dmap.chunks.audio.UnknownMQ.class); // aeMQ
		map.put(new Integer(0x61654652), org.dyndns.jkiddo.dmap.chunks.audio.UnknownFR.class); // aeFR
		map.put(new Integer(0x61655472), org.dyndns.jkiddo.dmap.chunks.audio.UnknownTr.class); // aeTr
		map.put(new Integer(0x6165534c), org.dyndns.jkiddo.dmap.chunks.audio.UnknownSL.class); // aeSL
		map.put(new Integer(0x61655352), org.dyndns.jkiddo.dmap.chunks.audio.UnknownSR.class); // aeSR
		map.put(new Integer(0x61654650), org.dyndns.jkiddo.dmap.chunks.audio.UnknownFP.class); // aeFP
		map.put(new Integer(0x61655358), org.dyndns.jkiddo.dmap.chunks.audio.UnknownSX.class); // aeSX
		map.put(new Integer(0x7070726f), org.dyndns.jkiddo.dmap.chunks.picture.ProtocolVersion.class); // ppro
		map.put(new Integer(0x6d736564), org.dyndns.jkiddo.dmap.chunks.media.Unknowned.class); // msed
		map.put(new Integer(0x6d736d6c), org.dyndns.jkiddo.dmap.chunks.media.Unknownml.class); // msml
		map.put(new Integer(0x6d737463), org.dyndns.jkiddo.dmap.chunks.media.Unknowntc.class); // mstc
		map.put(new Integer(0x6d73746f), org.dyndns.jkiddo.dmap.chunks.media.Unknownto.class); // msto
		map.put(new Integer(0x636d7061), org.dyndns.jkiddo.dmap.chunks.control.PairingContainer.class); // cmpa
		map.put(new Integer(0x636d7067), org.dyndns.jkiddo.dmap.chunks.control.PairingGuid.class); // cmpg
		map.put(new Integer(0x636d6e6d), org.dyndns.jkiddo.dmap.chunks.control.DeviceName.class); // cmnm
		map.put(new Integer(0x636d7479), org.dyndns.jkiddo.dmap.chunks.control.DeviceType.class); // cmty
		map.put(new Integer(0x63616369), org.dyndns.jkiddo.dmap.chunks.control.UnknownCI.class); // caci
		map.put(new Integer(0x636d696b), org.dyndns.jkiddo.dmap.chunks.control.UnknownIK.class); // cmik
		map.put(new Integer(0x636d7072), org.dyndns.jkiddo.dmap.chunks.control.UnknownPR.class); // cmpr
		map.put(new Integer(0x63617072), org.dyndns.jkiddo.dmap.chunks.control.UnknownCAPR.class); // capr
		map.put(new Integer(0x636d7370), org.dyndns.jkiddo.dmap.chunks.control.UnknownSP.class); // cmsp
		map.put(new Integer(0x636d7376), org.dyndns.jkiddo.dmap.chunks.control.UnknownSV.class); // cmsv
		map.put(new Integer(0x63617373), org.dyndns.jkiddo.dmap.chunks.control.UnknownSS.class); // cass
		map.put(new Integer(0x63616f76), org.dyndns.jkiddo.dmap.chunks.control.UnknownOV.class); // caov
		map.put(new Integer(0x63655347), org.dyndns.jkiddo.dmap.chunks.control.SavedGenius.class); // ceSG
		map.put(new Integer(0x636d726c), org.dyndns.jkiddo.dmap.chunks.control.UnknownRL.class); // cmrl
		map.put(new Integer(0x63655358), org.dyndns.jkiddo.dmap.chunks.control.UnknownCESX.class); // ceSX
		map.put(new Integer(0x61676172), org.dyndns.jkiddo.dmap.chunks.audio.ArtistSearchContainer.class); // agar
		map.put(new Integer(0x6d647374), org.dyndns.jkiddo.dmap.chunks.media.DownloadStatus.class); // mdst
		map.put(new Integer(0x61676163), org.dyndns.jkiddo.dmap.chunks.audio.UnknownAC.class); // agac
		map.put(new Integer(0x61737269), org.dyndns.jkiddo.dmap.chunks.audio.UnknownRI.class); // asri
		map.put(new Integer(0x63616976), org.dyndns.jkiddo.dmap.chunks.control.UnknownIV.class); // caiv
		map.put(new Integer(0x63616970), org.dyndns.jkiddo.dmap.chunks.control.UnknownIP.class); // caip
		map.put(new Integer(0x6165534e), org.dyndns.jkiddo.dmap.chunks.audio.UnknownSN.class); // aeSN
		map.put(new Integer(0x61654e4e), org.dyndns.jkiddo.dmap.chunks.audio.UnknownNN.class); // aeNN
		map.put(new Integer(0x6165454e), org.dyndns.jkiddo.dmap.chunks.audio.UnknownEN.class); // aeEN
		map.put(new Integer(0x61654553), org.dyndns.jkiddo.dmap.chunks.audio.UnknownES.class); // aeES
		map.put(new Integer(0x61655355), org.dyndns.jkiddo.dmap.chunks.audio.UnknownSU.class); // aeSU
		map.put(new Integer(0x61654748), org.dyndns.jkiddo.dmap.chunks.audio.UnknownGH.class); // aeGH
		map.put(new Integer(0x61654744), org.dyndns.jkiddo.dmap.chunks.audio.UnknownGD.class); // aeGD
		map.put(new Integer(0x61654755), org.dyndns.jkiddo.dmap.chunks.audio.UnknownGU.class); // aeGU
		map.put(new Integer(0x61654752), org.dyndns.jkiddo.dmap.chunks.audio.UnknownGR.class); // aeGR
		map.put(new Integer(0x61654745), org.dyndns.jkiddo.dmap.chunks.audio.UnknownGE.class); // aeGE
		map.put(new Integer(0x61736770), org.dyndns.jkiddo.dmap.chunks.audio.UnknownGp.class); // asgp
		map.put(new Integer(0x61736564), org.dyndns.jkiddo.dmap.chunks.audio.UnknownEd.class); // ased
		map.put(new Integer(0x61736870), org.dyndns.jkiddo.dmap.chunks.audio.UnknownHp.class); // ashp
		map.put(new Integer(0x6173736e), org.dyndns.jkiddo.dmap.chunks.audio.UnknownAssn.class); // assn
		map.put(new Integer(0x61737361), org.dyndns.jkiddo.dmap.chunks.audio.UnknownSa.class); // assa
		map.put(new Integer(0x6173736c), org.dyndns.jkiddo.dmap.chunks.audio.UnknownAssl.class); // assl
		map.put(new Integer(0x61737375), org.dyndns.jkiddo.dmap.chunks.audio.UnknownASSU.class); // assu
		map.put(new Integer(0x61737363), org.dyndns.jkiddo.dmap.chunks.audio.UnknownSc.class); // assc
		map.put(new Integer(0x61737373), org.dyndns.jkiddo.dmap.chunks.audio.UnknownSS.class); // asss
		map.put(new Integer(0x6173626b), org.dyndns.jkiddo.dmap.chunks.audio.UnknownBK.class); // asbk
		map.put(new Integer(0x61737075), org.dyndns.jkiddo.dmap.chunks.audio.UnknownPu.class); // aspu
		map.put(new Integer(0x61654352), org.dyndns.jkiddo.dmap.chunks.audio.UnknownCR.class); // aeCR
		map.put(new Integer(0x61736c73), org.dyndns.jkiddo.dmap.chunks.audio.UnknownLs.class); // asls
		map.put(new Integer(0x61655345), org.dyndns.jkiddo.dmap.chunks.audio.UnknownAESE.class); // aeSE
		map.put(new Integer(0x61654456), org.dyndns.jkiddo.dmap.chunks.audio.UnknownDV.class); // aeDV
		map.put(new Integer(0x61654450), org.dyndns.jkiddo.dmap.chunks.audio.UnknownDP.class); // aeDP
		map.put(new Integer(0x61736173), org.dyndns.jkiddo.dmap.chunks.audio.UnknownAs.class); // asas
		map.put(new Integer(0x61736c72), org.dyndns.jkiddo.dmap.chunks.audio.UnknownLr.class); // aslr
		map.put(new Integer(0x61737273), org.dyndns.jkiddo.dmap.chunks.audio.UnknownRs.class); // asrs
		map.put(new Integer(0x61736573), org.dyndns.jkiddo.dmap.chunks.audio.UnknownASES.class); // ases
		map.put(new Integer(0x61736b64), org.dyndns.jkiddo.dmap.chunks.audio.UnknownKd.class); // askd
		map.put(new Integer(0x61736163), org.dyndns.jkiddo.dmap.chunks.audio.UnknownASAC.class); // asac
		map.put(new Integer(0x61736b70), org.dyndns.jkiddo.dmap.chunks.audio.UnknownKp.class); // askp
		map.put(new Integer(0x6173706c), org.dyndns.jkiddo.dmap.chunks.audio.UnknownPl.class); // aspl
		map.put(new Integer(0x61654353), org.dyndns.jkiddo.dmap.chunks.audio.UnknownCs.class); // aeCS
		map.put(new Integer(0x61737063), org.dyndns.jkiddo.dmap.chunks.audio.UnknownPc.class); // aspc
		map.put(new Integer(0x61654d58), org.dyndns.jkiddo.dmap.chunks.audio.UnknownMX.class); // aeMX
		map.put(new Integer(0x61655844), org.dyndns.jkiddo.dmap.chunks.audio.UnknownXD.class); // aeXD
		map.put(new Integer(0x61654b32), org.dyndns.jkiddo.dmap.chunks.audio.UnknownK2.class); // aeK2
		map.put(new Integer(0x61654b31), org.dyndns.jkiddo.dmap.chunks.audio.UnknownK1.class); // aeK1
		map.put(new Integer(0x61654e44), org.dyndns.jkiddo.dmap.chunks.audio.UnknownND.class); // aeND
		map.put(new Integer(0x61654452), org.dyndns.jkiddo.dmap.chunks.audio.UnknownDR.class); // aeDR
		
	}

	public Class<? extends Chunk> getChunkClass(Integer contentCode)
	{
		return map.get(contentCode);
	}

	public Chunk newChunk(int contentCode)
	{
		Class<? extends Chunk> clazz = getChunkClass(new Integer(contentCode));
		try
		{
			return clazz.newInstance();
		}
		catch(Exception err)
		{
			throw new RuntimeException("Content code: " + DmapUtil.toContentCodeString(contentCode) + " not found. Hash is 0x" + Integer.toHexString(new Integer(contentCode)), err);
		}
	}
}
