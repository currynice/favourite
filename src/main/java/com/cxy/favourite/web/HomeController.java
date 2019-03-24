package com.cxy.favourite.web;

import com.cxy.favourite.common.aop.LogManage;
import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.enums.FollowStatus;
import com.cxy.favourite.domain.enums.IsDelete;
import com.cxy.favourite.jpa.FollowRepository;
import com.cxy.favourite.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

//    @Autowired
//    private CollectService collectService;
//    @Autowired
//    private FavoritesRepository favoritesRepository;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private CollectRepository collectRepository;
//    @Autowired
      private FollowRepository followRepository;
//    @Autowired
//    private NoticeService noticeService;
//    @Autowired
//    private LookRecordService lookRecordService;
//    @Autowired
//    private LetterService letterService;
//    @Autowired
//    private NoticeRepository noticeRepository;
/**
 * 个人首页
 */
    @RequestMapping(value = "/user/{userId}") //TODO  user/{userId}/{favouriteId}
    @LogManage(description = "个人首页")
    public String userPageShow(Model model, @PathVariable(value = "userId")Long userId, @RequestParam(value = "page",defaultValue = "0")Integer page,
                               @RequestParam(value = "size",defaultValue = "10")Integer size
                               ){
        User user = userRepository.getByUserId(userId);
        //基础排序规则
        //Long collectCount = 0l;
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"id"));
        //List<CollectSummary> collects = null;
        Integer isFollow = 0;
        if(userId.equals(getUserId() )){ //本人访问
            model.addAttribute("myself", IsDelete.YES.toString());
           // collectCount = collectRepository.countByUserIdAndIsDelete(userId,IsDelete.NO);
//            if(0 == favoritesId){
//                collects =collectService.getCollects("myself", userId, pageable,null,null);
//            }else{
//                collects =collectService.getCollects(String.valueOf(favoritesId), userId, pageable,0l,null);
//            }
        }else{
            model.addAttribute("myself",IsDelete.NO.toString());
//            collectCount = collectRepository.countByUserIdAndTypeAndIsDelete(userId, CollectType.PUBLIC, IsDelete.NO);
//            if(favoritesId == 0){
//                collects =collectService.getCollects("others", userId, pageable,null,getUserId());
//            }else{
//                collects = collectService.getCollects("otherpublic", userId, pageable, favoritesId,getUserId());
//            }
            isFollow = followRepository.countByUserIdAndFollowIdAndStatus(getUserId(), userId, FollowStatus.FOLLOW);
        }
//        Integer follow = followRepository.countByUserIdAndStatus(userId, FollowStatus.FOLLOW);
//        Integer followed = followRepository.countByFollowIdAndStatus(userId, FollowStatus.FOLLOW);
        //List<Favorites> favoritesList = favoritesRepository.findByUserId(userId);
//        List<String> followUser = followRepository.findFollowUserByUserId(userId);
//        List<String> followedUser = followRepository.findFollowedUserByFollowId(userId);
       // model.addAttribute("collectCount",collectCount);
//        model.addAttribute("follow",follow);
//        model.addAttribute("followed",followed);
        model.addAttribute("user",user);
      //  model.addAttribute("collects", collects);
     //   model.addAttribute("favoritesList",favoritesList);
//        model.addAttribute("followUser",followUser);
//        model.addAttribute("followedUser",followedUser);
 //       model.addAttribute("isFollow",isFollow);
        User userTemp = null;
        User currentUser = getUser();
        if(this.getUser()==null){
            userTemp = new User();
            userTemp.setId(0L);
        }
        model.addAttribute("loginUser",currentUser == null ? userTemp : currentUser);
        return "user";
    }
}
