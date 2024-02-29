import AllFriends from "./AllFriends";
import TopHeader from "./TopHeader";
import TopSearch from "./TopSearch";
import styles from "./css/LeftSideBar.module.css"
const LeftSideBar=()=>{
    return(
        <div className={styles.leftSideBarContainer}>
        <TopHeader/>
        <TopSearch/>
        <AllFriends/>
        </div>
    )
}
export default LeftSideBar;