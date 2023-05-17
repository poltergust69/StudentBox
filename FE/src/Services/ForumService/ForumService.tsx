import { PostModel } from "../../Models/Forum/ForumInterfaces";
import {
  PageModel,
  PaginationState,
} from "../../Models/Shared/SharedInterfaces";
import { isLoggedIn } from "../AuthService/AuthService";
import { getBaseInstance, getBaseAuthInstance } from "../Shared/AxiosService";

export const getPageOfForumPosts = async (
  paginationParams: PaginationState
): Promise<PageModel<PostModel>> => {
  const instance = (await isLoggedIn())
    ? await getBaseAuthInstance()
    : getBaseInstance();

  return instance
    .get("forum/posts", {
      params: paginationParams,
    })
    .then((response) => {
      return response.data;
    });
};

export const togglePostLike = async (id: string): Promise<boolean> => {
  const instance = (await isLoggedIn())
    ? await getBaseAuthInstance()
    : getBaseInstance();

  return instance
    .put(`forum/posts/${id}/like`)
    .then(() => {
      return true;
    })
    .catch(() => {
      return false;
    });
};

export const toggleReplyLike = async (
  postId: string,
  replyId: string
): Promise<boolean> => {
  const instance = (await isLoggedIn())
    ? await getBaseAuthInstance()
    : getBaseInstance();

  return instance
    .put(`forum/posts/${postId}/replies/${replyId}/like`)
    .then(() => {
      return true;
    })
    .catch(() => {
      return false;
    });
};
