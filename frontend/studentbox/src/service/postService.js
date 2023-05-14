import axios from "../custom-axios/axios";

const postService = {

    fetchAllPosts: () => axios.get("/posts"),

    fetchPost: (postId) => axios.get(`/posts/${postId}`),

    addPost: (postCreationModel) => axios.post("/posts",{
       "postCreationModel":postCreationModel
    }),

    updatePost: (postId,postCreationModel) => axios.patch(`/posts/${postId}`,{
       "postCreationModel":postCreationModel
        }
    ),

    deletePost: (postId) => axios.delete(`/posts/${postId}`),

    likePost: (postId) => axios.put(`/posts/${postId}/like`),

    addReply : (postId, postReplyCreationModel) => axios.post(`/posts/${postId}/reply`,{
        "postReplyCreationModel":postReplyCreationModel
    }),

    updateReply: (postId, replyId, postReplyCreationModel) => axios.patch(`/posts/${postId}/reply/${replyId}/`,{
        "postReplyCreationModel":postReplyCreationModel
    }),

    deleteReply: (postId, replyId) => axios.delete(`/posts/${postId}/reply/${replyId}`),

    likeReply: (postId, replyId) => axios.patch(`/posts/${postId}/reply/${replyId}/like`)
}

export default postService
