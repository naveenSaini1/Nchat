import { useContext } from "react";
import ChatMessageContainer from "./ChatMessageContainer";
import RightTopHeader from "./RightTopHeader";
import SendMessage from "./SendMessage";
import styles from "./css/RightSideBar.module.css"
import { UserContext } from "../../../contextapi/UserContext";

const RightSideBar=()=>{
    const {currentUser}=useContext(UserContext);
    return   <>
        {
        (currentUser!=null)?
        <div className={styles.rightSideBar}>
        <RightTopHeader/>
        <ChatMessageContainer/>
        <SendMessage/>
        </div>
        :<div className={styles.rightSideBar} style={{display:"flex",justifyContent:"center",alignItems:"center"}} >
           <p style={{fontSize: "47px",fontFamily:"DM Sans Merlin"}}> Enjoy NChat </p>
        </div>
        }
        </>
    
}
export default RightSideBar;