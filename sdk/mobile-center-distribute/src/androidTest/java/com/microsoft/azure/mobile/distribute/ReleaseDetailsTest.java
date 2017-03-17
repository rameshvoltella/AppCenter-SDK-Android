package com.microsoft.azure.mobile.distribute;

import android.net.Uri;

import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ReleaseDetailsTest {

    @Test
    public void parse() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails releaseDetails = ReleaseDetails.parse(json);
        assertNotNull(releaseDetails);
        assertEquals(42, releaseDetails.getId());
        assertEquals(14, releaseDetails.getVersion());
        assertEquals("2.1.5", releaseDetails.getShortVersion());
        assertEquals("Fix a critical bug, this text was entered in Mobile Center portal.", releaseDetails.getReleaseNotes());
        assertEquals(19, releaseDetails.getMinApiLevel());
        assertEquals(Uri.parse("http://download.thinkbroadband.com/1GB.zip"), releaseDetails.getDownloadUrl());
        assertEquals("9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60", releaseDetails.getReleaseHash());
    }

    @Test(expected = JSONException.class)
    public void missingId() throws JSONException {
        String json = "{" +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test(expected = JSONException.class)
    public void invalidId() throws JSONException {
        String json = "{" +
                "id: '42abc'," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test
    public void acceptIdAsString() throws JSONException {
        String json = "{" +
                "id: '42'," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails releaseDetails = ReleaseDetails.parse(json);
        assertNotNull(releaseDetails);
        assertEquals(42, releaseDetails.getId());
        assertEquals(14, releaseDetails.getVersion());
        assertEquals("2.1.5", releaseDetails.getShortVersion());
        assertEquals("Fix a critical bug, this text was entered in Mobile Center portal.", releaseDetails.getReleaseNotes());
        assertEquals(19, releaseDetails.getMinApiLevel());
        assertEquals(Uri.parse("http://download.thinkbroadband.com/1GB.zip"), releaseDetails.getDownloadUrl());
        assertEquals("9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60", releaseDetails.getReleaseHash());
    }

    @Test(expected = JSONException.class)
    public void missingVersion() throws JSONException {
        String json = "{" +
                "id: 42," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test(expected = JSONException.class)
    public void invalidVersion() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: true," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test(expected = JSONException.class)
    public void missingShortVersion() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test
    public void missingReleaseNotes() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "android_min_api_level: 19," +
                "download_url: 'https://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails releaseDetails = ReleaseDetails.parse(json);
        assertNotNull(releaseDetails);
        assertEquals(42, releaseDetails.getId());
        assertEquals(14, releaseDetails.getVersion());
        assertEquals("2.1.5", releaseDetails.getShortVersion());
        assertNull(releaseDetails.getReleaseNotes());
        assertEquals(19, releaseDetails.getMinApiLevel());
        assertEquals(Uri.parse("https://download.thinkbroadband.com/1GB.zip"), releaseDetails.getDownloadUrl());
        assertEquals("9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60", releaseDetails.getReleaseHash());
    }

    @Test
    public void nullReleaseNotes() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "release_notes: null," +
                "android_min_api_level: 19," +
                "short_version: '2.1.5'," +
                "download_url: 'https://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails releaseDetails = ReleaseDetails.parse(json);
        assertNotNull(releaseDetails);
        assertEquals(42, releaseDetails.getId());
        assertEquals(14, releaseDetails.getVersion());
        assertEquals("2.1.5", releaseDetails.getShortVersion());
        assertNull(releaseDetails.getReleaseNotes());
        assertEquals(19, releaseDetails.getMinApiLevel());
        assertEquals(Uri.parse("https://download.thinkbroadband.com/1GB.zip"), releaseDetails.getDownloadUrl());
        assertEquals("9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60", releaseDetails.getReleaseHash());
    }

    @Test(expected = JSONException.class)
    public void missingApiLevel() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test
    public void acceptApiLevelAsString() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: '19'," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails releaseDetails = ReleaseDetails.parse(json);
        assertNotNull(releaseDetails);
        assertEquals(42, releaseDetails.getId());
        assertEquals(14, releaseDetails.getVersion());
        assertEquals("2.1.5", releaseDetails.getShortVersion());
        assertEquals("Fix a critical bug, this text was entered in Mobile Center portal.", releaseDetails.getReleaseNotes());
        assertEquals(19, releaseDetails.getMinApiLevel());
        assertEquals(Uri.parse("http://download.thinkbroadband.com/1GB.zip"), releaseDetails.getDownloadUrl());
        assertEquals("9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60", releaseDetails.getReleaseHash());
    }

    @Test(expected = JSONException.class)
    public void invalidApiLevel() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: '4.0.3'," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test(expected = JSONException.class)
    public void missingDownloadUrl() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19" +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test(expected = JSONException.class)
    public void missingDownloadUrlScheme() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'someFile'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test(expected = JSONException.class)
    public void invalidDownloadUrlScheme() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'ftp://someFile'," +
                "package_hashes: ['9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60']" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test(expected = JSONException.class)
    public void missingPackageHashes() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test(expected = JSONException.class)
    public void emptyPackageHashes() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: []" +
                "}";
        ReleaseDetails.parse(json);
    }

    @Test(expected = JSONException.class)
    public void invalidPackageHashes() throws JSONException {
        String json = "{" +
                "id: 42," +
                "version: '14'," +
                "short_version: '2.1.5'," +
                "release_notes: 'Fix a critical bug, this text was entered in Mobile Center portal.'," +
                "android_min_api_level: 19," +
                "download_url: 'http://download.thinkbroadband.com/1GB.zip'," +
                "package_hashes: '9f52199c986d9210842824df695900e1656180946212bd5e8978501a5b732e60'" +
                "}";
        ReleaseDetails.parse(json);
    }
}