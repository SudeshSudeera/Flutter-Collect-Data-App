package com.oznhub.oznhubcollectdataserviceapi.entity.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.sql.Blob;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerAvatarFileResource {
    private Blob avatarHash;
    private String avatarDirectory;
    private Blob avatarFileName;
    private Blob avatarResourceUrl;
}
