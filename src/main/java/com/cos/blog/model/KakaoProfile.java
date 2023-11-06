package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Generated;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@Generated("jsonschema2pojo")
public class KakaoProfile {
	public Long id;
	public String connectedAt;
	public Properties properties;
	public KakaoAccount kakaoAccount;
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	@Data
	public class Properties {
		public String nickname;
		public String profileImage;
		public String thumbnailImage;
	}
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	@Data
	public class KakaoAccount {
		public Boolean profileNicknameNeedsAgreement;
		public Boolean profileImageNeedsAgreement;
		public Profile profile;
		
		@JsonIgnoreProperties(ignoreUnknown=true)
		@Data
		public class Profile {
			public String nickname;
			public String thumbnailImageUrl;
			public String profileImageUrl;
			public Boolean isDefaultImage;
			}	
		
	}
}
