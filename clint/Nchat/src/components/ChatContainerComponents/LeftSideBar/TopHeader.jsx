import styles from "./css/TopHeader.module.css"
import { IoMdMore } from "react-icons/io";
import commonStyle from "./../../../CommonCss/CommonCss.module.css"
import { useContext } from "react";
import { UserContext } from "../../../contextapi/UserContext";

const TopHeader=()=>{
    const {user}=useContext(UserContext);

    return (
        <div className={styles.topHeader}>
            <div className={styles.image}>
            <img src={`http://localhost:8080/img/${user?.image}`} alt="" />
            </div>
            <div className={commonStyle.moreImp}>
            <IoMdMore />
            </div>
        </div>
    )
}
export default TopHeader;