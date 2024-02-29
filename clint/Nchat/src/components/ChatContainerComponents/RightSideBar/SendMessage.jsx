import { useContext, useRef } from "react";
import styles from "./css/SendMessage.module.css"
import { UserContext } from "../../../contextapi/UserContext";
const SendMessage=()=>{
    console.log("send message main function")
    const {user,sendMessage,currentUser}=useContext(UserContext);
    const messageRef=useRef();
    const handleMessage=()=>{
        let obj={
            senderEmailId:user.emailId,
            receiverEmailId:currentUser.emailId,
            message:messageRef.current.value
        }
        sendMessage(obj);
        messageRef.current.value="";
    }

    return(
        <div className={styles.messageContainer}>

        <input ref={messageRef} className={styles.sendMessageInput} type="search" name="search" id="search" placeholder="Enter the " />
        <button onClick={handleMessage} className={styles.sendMessageBtn}>Send</button>
        
        </div>
    )

}
export default SendMessage;