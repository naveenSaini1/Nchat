import LeftSideBar from "../ChatContainerComponents/LeftSideBar/LeftSideBar";
import RightSideBar from "../ChatContainerComponents/RightSideBar/RightSideBar";
import styles from "./ChatContainer.module.css";
const ChatContainer=()=>{
    return (
        <div className={styles.mainContainer}> 
        <LeftSideBar/>
        <RightSideBar/>
        </div>
    )
}
export default ChatContainer;