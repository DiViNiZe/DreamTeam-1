import React from 'react'
import axios from '../../util/axios'
import Video from '../../components/Video'
import NavBar from '../../components/NavBar'
import VideoTitle from '../../components/videotitle'
import { withRouter } from 'next/router'

export default class index extends React.Component{
  constructor(){
    super()
    this.state = {
      video:{
        videoPath:'',
        lectuler:''
      }
    }
  }

  async componentDidMount() {
    const {router} = this.props
    const videoId = this.props.url.query.video_id
    let {data}  =await axios.get(`video-service/video/${videoId}`)
    this.setState({video:data})
    console.log(this.state.video.videoPath)
    console.log('router : ',router)

  }

  render(){
    return (
      <div>
        <NavBar />
        <VideoTitle
        videotitle={this.state.video.videoName}
        teacher={this.state.video.lectuler}
         />
        <div className='justify-content-md-center' style={{width:'100%',display:'flex'}}>
          <Video style={{justifyContent:'center'}} video={this.state.video} playing controls />
        </div>
        
      </div>
    )
  }
}