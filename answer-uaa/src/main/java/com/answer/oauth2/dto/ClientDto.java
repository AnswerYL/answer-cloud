/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.dto
 * @className com.answer.oauth2.dto.ClientDto
 */
package com.answer.oauth2.dto;

import com.answer.oauth2.model.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * ClientDto
 * @description
 * @author answer_wx
 * @date 2024/1/24 17:16
 * @version 1.0
 */
@Getter
@Setter
public class ClientDto extends Client {
    private static final long serialVersionUID = 1475637288060027265L;

    private List<Long> permissionIds;

    private Set<Long> serviceIds;
}