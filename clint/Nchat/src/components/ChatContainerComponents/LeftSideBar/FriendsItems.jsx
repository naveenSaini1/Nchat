import { useContext, useEffect, useRef } from "react";
import styles from "./css/AllFriends.module.css"
import { UserContext } from "../../../contextapi/UserContext";
const FriendsItems=({item})=>{
    const {hanledCurretUser,updateMessageStatus}=useContext(UserContext);
    const messageNotificationRef=useRef();
    messageNotificationRef.current=item.messages.filter((msg)=>(msg.status==false && item.emailId!=msg.receiverid)).length
    
    console.log(item,"frendsItems componenet")
    const hadleCurrentUserMethod=()=>{
        console.log(item);
        hanledCurretUser(item);
        if(messageNotificationRef.current>0){
            updateMessageStatus(item)
        }
        
             
    }
    return (
        <div  onClick={hadleCurrentUserMethod} className={styles.friendsItemContainer}>
            <div className={styles.friendsIcons}>
               { (item?.active==true) &&<div className={styles.activeFriend}></div>}
                <img src={`http://localhost:8080/img/${item.image}`} alt="" />
            </div>
            <div className={styles.friendsBio}>
                <p>{item?.name}</p>
                <p>{item?.bio}</p>

            </div>
            <div className={styles.notificonsIcons}>
            {(messageNotificationRef.current)>0 && <p>{messageNotificationRef.current}</p>}
            </div>
        
        </div>
    )
}
export default FriendsItems;