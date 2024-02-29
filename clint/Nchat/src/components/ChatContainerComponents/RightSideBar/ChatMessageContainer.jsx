import { useContext } from "react";
import styles from "./css/ChatMessageContainer.module.css"
import { UserContext } from "../../../contextapi/UserContext";
const ChatMessageContainer = () => {
    const { userMainData, currentUser, user } = useContext(UserContext);
    return (
        <>
            <div className={styles.chatMessageContainer} >
                {userMainData?.friends.map((item) => {
                    if (item.emailId == currentUser.emailId) {
                        return item.messages.map((sub_item) => {
                            return (sub_item.receiverid != user.emailId) ?
                                <div key={sub_item.messageId} className={styles.leftP}>
                                    <div className={styles.leftPDiv}>
                                        <p>{sub_item.message}</p>
                                        <div className={styles.seenMessageMainContainer}>
                                        <div>
                                            <div className={`${styles.logoFirstDiv} ${(sub_item.status) ? styles.seenSignBorderBlue : styles.seenSignBordergray}`} ></div>
                                            <div className={`${styles.logoSecDiv} ${(sub_item.status) ? styles.seenSignBorderBlue : styles.seenSignBordergray}`}></div>
                                        </div>
                                        <div>
                                            <div className={`${styles.logoFirstDiv} ${(sub_item.status) ? styles.seenSignBorderBlue : styles.seenSignBordergray}`} style={{ top: "77%" }}></div>
                                            <div className={`${styles.logoSecDiv} ${(sub_item.status) ? styles.seenSignBorderBlue : styles.seenSignBordergray}`} style={{ top: "61%" }}></div>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                                :
                                <div key={sub_item.messageId} className={styles.rightP}> <p >{sub_item.message}</p>
                                </div>
                        })
                    }
                })}

            </div>

        </>
    )

}
export default ChatMessageContainer;