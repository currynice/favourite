//package com.cxy.favourite.jpa;
//
//import com.cxy.favourite.domain.DTO.UserDTO;
//import com.cxy.favourite.domain.Follow;
//import com.cxy.favourite.domain.User;
//import com.cxy.favourite.domain.enums.FollowStatus;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.persistence.EntityManager;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class FollowRepositoryTest {
//
//
//    @Autowired
//    private FollowRepository followRepository;
//
//
//
//
//
//
//    @Test
//    public void countByUserIdAndStatus() throws Exception {
//        Follow follow = new Follow();
//        follow.setStatus(FollowStatus.FOLLOW);
//        System.out.println(followRepository.countByUserIdAndStatus(3L,follow.getStatus()));
//    }
//
//    @Test
//    public void countByFollowIdAndStatus() throws Exception {
//        Follow follow = new Follow();
//        follow.setStatus(FollowStatus.FOLLOW);
//        System.out.println(followRepository.countByFollowIdAndStatus(3L,follow.getStatus()));
//    }
//
//    @Test
//    public void countByUserIdAndFollowIdAndStatus() throws Exception {
//        System.out.println(followRepository.countByUserIdAndFollowIdAndStatus(4L,3L,FollowStatus.FOLLOW));
//    }
//
//    @Test
//    public void findByUserId() throws Exception {
//        List<String> result = this.followRepository.findByUserId(3L);
//        for (String s:result){
//            System.out.println(s);
//        }
//    }
//
//    @Test
//    public void findMyFollowIdByUserIdId() throws Exception {
//        List<Long> result = this.followRepository.findMyFollowIdByUserId(3L);
//        for (long s:result){
//            System.out.println(s);
//        }
//    }
//
//    @Test
//    public void findFollowUserByUserId() throws Exception {
//        List<Object> resultList = new ArrayList<>();
//        List<Object[]> result = this.followRepository.findFollowUserByUserId(3L);
//            for(Object row: result){
//                Object[] rowArray = (Object[])row;
//                Map<String, Object> mapArr = new HashMap<String, Object>();
//                mapArr.put("userName",rowArray[0]);
//                mapArr.put("introduction",rowArray[1]);
//                mapArr.put("myPicture",rowArray[2]);
//                mapArr.put("userId",rowArray[3]);
//                resultList.add(mapArr);
//            }
//            //return resultList
//         System.out.println(resultList);
//
//    }
//    /**
//     *  List<Object> resultList = new ArrayList<>();
//     List<Object[]> result = XXXXService.selectListXXX(Id);
//     for (Object row : result) {
//     Object[] rowArray = (Object[]) row;
//     Map<String, Object> mapArr = new HashMap<String, Object>();
//     mapArr.put("id", rowArray[0]);
//     mapArr.put("name", rowArray[1]);
//     mapArr.put("sex", rowArray[2]);
//     resultList.add(mapArr);
//     }
//     return resultList;
//     */
//
//    @Test
//    public void findFollowedUserByFollowId() throws Exception {
//
//        List<Object[]> result = this.followRepository.findFollowedUserByFollowId(3L);
//
//    List<UserDTO> list =  result.stream().map(tuple ->{
//                //创建dto
//                UserDTO dto = new UserDTO();
//                dto.setUserName(tuple[0].toString());
//                dto.setIntroduction(tuple[1].toString());
//                dto.setMyPicture(tuple[2].toString());
//                dto.setUserId((Long)tuple[3]);
//                return dto;
//            }).collect(Collectors.toList());
//            System.out.println(list);
//    }
//
//    @Test
//    public void updateStatusById(){
//        this.followRepository.updateStatusById(FollowStatus.FOLLOW
//        ,System.currentTimeMillis(),1L);
//
//    }
//
//
//}