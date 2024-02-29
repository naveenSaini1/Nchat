import { useContext } from "react";
import styles from "./css/AllFriends.module.css"
import FriendsItems from "./FriendsItems";
import { UserContext } from "../../../contextapi/UserContext";
const AllFriends=()=>{
    const {userMainData}=useContext(UserContext);
    console.log("all freinds compoenent",userMainData);
    return (
        <div style={{overflow:"auto",height:"100%"}}>
        {userMainData?.friends?.map((item)=>{
            return <FriendsItems item={item} key={item.emailId}/>
        })}
        
        
        </div>
    )
}
export default AllFriends;