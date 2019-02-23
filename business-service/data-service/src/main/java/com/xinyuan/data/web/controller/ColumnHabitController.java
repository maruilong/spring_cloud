package com.xinyuan.data.web.controller;

import com.xinyuan.data.entity.ColumnHabit;
import com.xinyuan.data.service.ColumnHabitService;
import com.xinyuan.data.web.request.HabitQueryRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang
 */
@Api(description = "用户栏目习惯")
@RestController
@RequestMapping("columnHabit")
public class ColumnHabitController {

    @Autowired
    private ColumnHabitService columnHabitService;

    @ApiImplicitParam(name = "columnHabit", value = "columnHabit", required = true, dataType = "ColumnHabit")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<ColumnHabit> add(@RequestBody ColumnHabit columnHabit) {

        ColumnHabit save = columnHabitService.save(columnHabit);

        return ResponseEntity.ok(save);
    }

    @ApiImplicitParam(name = "columnHabit", value = "columnHabit", required = true, dataType = "ColumnHabit")
    @RequestMapping(value = "getByUser", method = RequestMethod.POST)
    public ResponseEntity<ColumnHabit> getByUser(@RequestBody HabitQueryRequest habitQueryRequest) {

        ColumnHabit columnHabit = columnHabitService.getByUser(habitQueryRequest);

        return ResponseEntity.ok(columnHabit);
    }
}
