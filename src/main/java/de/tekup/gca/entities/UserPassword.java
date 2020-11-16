package de.tekup.gca.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPassword {

	private Long id;
	private String oldPassword;
	private String newPassword;
}
