package com.yupi.springbootinit.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.DeleteRequest;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.model.dto.post.PostAddRequest;
import com.yupi.springbootinit.model.dto.post.PostQueryRequest;
import com.yupi.springbootinit.model.dto.post.PostUpdateRequest;
import com.yupi.springbootinit.model.entity.Post;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.service.PostService;
import com.yupi.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ���ӽӿ�
 */
@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    // region ��ɾ�Ĳ�

    /**
     * ����
     *
     * @param postAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addPost(@RequestBody PostAddRequest postAddRequest, HttpServletRequest request) {
        if (postAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postAddRequest, post);
        List<String> tags = postAddRequest.getTags();
        if (tags != null) {
            post.setTags(JSONUtil.toJsonStr(tags));
        }
        User loginUser = userService.getLoginUser(request);
        post.setUserId(loginUser.getId());
        boolean result = postService.save(post);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newPostId = post.getId();
        return ResultUtils.success(newPostId);
    }

    /**
     * ɾ��
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePost(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // �ж��Ƿ����
        Post oldPost = postService.getById(id);
        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
        // �����˻����Ա��ɾ��
        if (!oldPost.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = postService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * ���£�������Ա��
     *
     * @param postUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updatePost(@RequestBody PostUpdateRequest postUpdateRequest, HttpServletRequest request) {
        if (postUpdateRequest == null || postUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        Post post = new Post();
        BeanUtils.copyProperties(postUpdateRequest, post);
        List<String> tags = postUpdateRequest.getTags();
        if (tags != null) {
            post.setTags(JSONUtil.toJsonStr(tags));
        }
        long id = postUpdateRequest.getId();
        // �ж��Ƿ����
        Post oldPost = postService.getById(id);
        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
        // �����˻����Ա�ɸ���
        if (!oldPost.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = postService.updateById(post);
        return ResultUtils.success(result);
    }


    /**
     * ���� id ��ȡ����
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Post> getPostById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = postService.getById(id);
        if (post == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(postService.getById(post));
    }

    /**
     * ��ҳ��ȡ�����б�
     *
     * @param postQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Post>> listPostByPage(@RequestBody PostQueryRequest postQueryRequest) {
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getPageSize();
        // ��������
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        return ResultUtils.success(postPage);
    }

    // endregion

    /**
     * ��ҳ�������� ES ��ѯ��
     *
     * @param postQueryRequest
     * @return
     */
    @PostMapping("/search/page")
    public BaseResponse<Page<Post>> searchPostByPage(@RequestBody PostQueryRequest postQueryRequest) {
        long size = postQueryRequest.getPageSize();
        // ��������
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return ResultUtils.success(postPage);
    }

}
