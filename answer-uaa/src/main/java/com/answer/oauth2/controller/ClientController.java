/**
 * @projectName answer-cloud
 * @package com.answer.oauth2.controller
 * @className com.answer.oauth2.controller.ClientController
 */
package com.answer.oauth2.controller;

import com.answer.common.model.PageResult;
import com.answer.common.model.Result;
import com.answer.oauth2.dto.ClientDto;
import com.answer.oauth2.model.Client;
import com.answer.oauth2.service.IClientService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClientController
 * @description 应用相关接口
 * @author answer_wx
 * @date 2024/1/24 17:19
 * @version 1.0
 */
@Api(tags = "应用相关api")
@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping("/list")
    @ApiOperation(value = "应用列表")
    public PageResult<Client> list(@RequestParam Map<String, Object> params) {
        return clientService.listClient(params, true);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取应用")
    public Client get(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有应用")
    public Result<List<Client>> allClient() {
        PageResult<Client> page = clientService.listClient(Maps.newHashMap(), false);
        return Result.succeed(page.getData());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除应用")
    public void delete(@PathVariable Long id) {
        clientService.delClient(id);
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "保存或者修改应用")
    public Result saveOrUpdate(@RequestBody ClientDto clientDto) throws Exception {
        return clientService.saveClient(clientDto);
    }
}