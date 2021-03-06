package com.changgou.goods.controller;

import com.changgou.framework.Entity.Result;
import com.changgou.framework.Entity.StatusCode;
import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    //逻辑删除
    @PutMapping("/restore/{id}")
    public Result restore(@PathVariable("id") Long id) {
        spuService.restore(id);
        return new Result(true, StatusCode.OK, "逻辑还原成功");
    }

    //逻辑删除
    @DeleteMapping("/logic/delete/{id}")
    public Result logicDelete(@PathVariable("id") Long id) {
        spuService.logicDelete(id);
        return new Result(true, StatusCode.OK, "逻辑删除成功");
    }

    //批量上架
    @PutMapping("/pull/many")
    public Result pillMany(@RequestBody Long[] ids) {
        Integer integer = spuService.pullMany(ids);
        return new Result(true, StatusCode.OK, "批量下架成功,下架" + integer + "条数据!");
    }

    //批量上架
    @PutMapping("/put/many")
    public Result putMany(@RequestBody Long[] ids) {
        Integer integer = spuService.putMany(ids);
        return new Result(true, StatusCode.OK, "批量上架成功,上架" + integer + "条数据!");
    }

    //上架商品
    @PutMapping("/put/{id}")
    public Result put(@PathVariable("id") Long spuId) {
        spuService.put(spuId);
        return new Result(true, StatusCode.OK, "商品上架成功!");
    }


    //下架商品
    @PutMapping("/pull/{id}")
    public Result pull(@PathVariable("id") Long spuId) {
        spuService.pull(spuId);
        return new Result(true, StatusCode.OK, "商品下架成功!");
    }


    //通过审核自动上架
    @PutMapping("/audit/{id}")
    public Result audit(@PathVariable("id") Long spuId) {
        spuService.audit(spuId);
        return new Result(true, StatusCode.OK, "商品审核成功");
    }

    //根据spuId查询Goods信息
    @GetMapping("/goods/{id}")
    public Result<Goods> findGoodsById(@PathVariable("id") Long spuId) {
        Goods goodsById = spuService.findGoodsById(spuId);
        return new Result<Goods>(true, StatusCode.OK, "查询goods数据成功", goodsById);
    }


    //保存goods
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods) {
        spuService.saveGoods(goods);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    /***
     * Spu分页条件搜索实现
     * @param spu
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Spu spu, @PathVariable int page, @PathVariable int size) {
        //调用SpuService实现分页条件查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(spu, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Spu分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用SpuService实现分页查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param spu
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Spu>> findList(@RequestBody(required = false) Spu spu) {
        //调用SpuService实现条件查询Spu
        List<Spu> list = spuService.findList(spu);
        return new Result<List<Spu>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        //调用SpuService实现根据主键删除
        spuService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Spu数据
     * @param spu
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Spu spu, @PathVariable String id) {
        //设置主键值
        spu.setId(id);
        //调用SpuService实现修改Spu
        spuService.update(spu);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Spu数据
     * @param spu
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Spu spu) {
        //调用SpuService实现添加Spu
        spuService.add(spu);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Spu数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable String id) {
        //调用SpuService实现根据主键查询Spu
        Spu spu = spuService.findById(id);
        return new Result<Spu>(true, StatusCode.OK, "查询成功", spu);
    }

    /***
     * 查询Spu全部数据
     * @return
     */
    @GetMapping
    public Result<List<Spu>> findAll() {
        //调用SpuService实现查询所有Spu
        List<Spu> list = spuService.findAll();
        return new Result<List<Spu>>(true, StatusCode.OK, "查询成功", list);
    }
}
