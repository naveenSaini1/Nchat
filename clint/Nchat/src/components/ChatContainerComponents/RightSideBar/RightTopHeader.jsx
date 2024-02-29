import styles from "./css/RightTopHeader.module.css"
import { IoMdMore } from "react-icons/io";
import commonStyle from "./../../../CommonCss/CommonCss.module.css"
import { useContext } from "react";
import { UserContext } from "../../../contextapi/UserContext";

const RightTopHeader=()=>{
    const {currentUser}=useContext(UserContext);

    return (
        <div className={styles.topHeader}>
            <div className={styles.topHeaderUserInfo}>
                <div className={styles.topImage}>
                    <img src={`http://localhost:8080/img/${currentUser.image}`} alt="" />
                </div>
                <p style={{marginLeft:"10px"}}>{currentUser?.name}</p>
            </div>
            <div className={commonStyle.moreImp}>
                <IoMdMore/>
            </div>


        </div>
    )
}
export default RightTopHeader;