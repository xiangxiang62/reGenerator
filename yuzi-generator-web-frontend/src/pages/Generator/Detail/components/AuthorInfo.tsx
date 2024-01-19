import {Avatar, Card} from 'antd';
import React from 'react';

interface Props {
  data:API.GeneratorVO;
}

/**
 * 作者信息
 *
 * @param props
 * @constructor
 */
const AuthorInfo:React.FC<Props> = (props) => {

  const {data} = props;

  const user = data?.user;

  if (!user){
    return <></>
  }

  return (
    <div style={{ marginTop: 16}}>
      <Card.Meta title={user.userName} description={user.userProfile} avatar={
        <Avatar size={64} src={user.userAvatar}/>
      }/>
    </div>
  )

}


export default AuthorInfo;
