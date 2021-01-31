package com.victorlh.spotify.apiclient.services.browse.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Getter
public class RecommendationsRequest {

	private final Integer limit;
	private final CountryCode market;
	@Singular
	private final List<String> seedArtists;
	@Singular
	private final List<String> seedGenres;
	@Singular
	private final List<String> seedTracks;
	private final Number minAcousticness;
	private final Number maxAcousticness;
	private final Number targetAcousticness;
	private final Number minDanceability;
	private final Number maxDanceability;
	private final Number targetDanceability;
	private final Number minDurationMs;
	private final Number maxDurationMs;
	private final Number targetDurationMs;
	private final Number minEnergy;
	private final Number maxEnergy;
	private final Number targetEnergy;
	private final Number minInstrumentalness;
	private final Number maxInstrumentalness;
	private final Number targetInstrumentalness;
	private final Number minKey;
	private final Number maxKey;
	private final Number targetKey;
	private final Number minLiveness;
	private final Number maxLiveness;
	private final Number targetLiveness;
	private final Number minLoudness;
	private final Number maxLoudness;
	private final Number targetLoudness;
	private final Number minMode;
	private final Number maxMode;
	private final Number targetMode;
	private final Number minPopularity;
	private final Number maxPopularity;
	private final Number targetPopularity;
	private final Number minSpeechiness;
	private final Number maxSpeechiness;
	private final Number targetSpeechiness;
	private final Number minTempo;
	private final Number maxTempo;
	private final Number targetTempo;
	private final Number minTimeSignature;
	private final Number maxTimeSignature;
	private final Number targetTimeSignature;
	private final Number minValence;
	private final Number maxValence;
	private final Number targetValence;

	public List<NameValuePair> getParams() {
		ArrayList<NameValuePair> params = new ArrayList<>();
		if (limit != null) {
			params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
		}
		if (market != null) {
			params.add(new BasicNameValuePair("market", market.getAlpha2()));
		}
		if (seedArtists != null && !seedArtists.isEmpty()) {
			params.add(new BasicNameValuePair("seed_artists", String.join(",", seedArtists)));
		}
		if (seedGenres != null && !seedGenres.isEmpty()) {
			params.add(new BasicNameValuePair("seed_genres", String.join(",", seedGenres)));
		}
		if (seedTracks != null && !seedTracks.isEmpty()) {
			params.add(new BasicNameValuePair("seed_tracks", String.join(",", seedTracks)));
		}

		Class<RecommendationsRequest> aClass = RecommendationsRequest.class;
		List<Field> fields = Arrays.asList(aClass.getDeclaredFields());
		List<NameValuePair> collect = fields.stream()
				.map(this::getFieldData)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		params.addAll(collect);

		return params;
	}

	private NameValuePair getFieldData(Field field) {
		if (!field.getType().isAssignableFrom(Number.class)) {
			return null;
		}
		Object value = null;
		try {
			value = field.get(this);
		} catch (IllegalAccessException ignored) {
		}
		if (value == null) {
			return null;
		}
		String name = parseFieldName(field);
		String valueStr = String.valueOf(value);
		return new BasicNameValuePair(name, valueStr);

	}

	private String parseFieldName(Field field) {
		String name = field.getName();
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < name.length(); i++) {
			String c = name.substring(i, i + 1);
			String cUpper = StringUtils.toRootUpperCase(c);
			if (StringUtils.equals(c, cUpper)) {
				out.append("_");
				out.append(StringUtils.toRootLowerCase(c));
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}

	public static void main(String[] args) {
		RecommendationsRequest recommendationsRequest = RecommendationsRequest.builder()
				.limit(111)
				.market(CountryCode.ES)
				.seedArtist("asd")
				.seedArtist("dsa")
				.minAcousticness(10)
				.maxAcousticness(20)
				.targetAcousticness(15)
				.build();
		List<NameValuePair> paramString = recommendationsRequest.getParams();
		System.out.println(paramString);
	}
}
