import "./ForumPage.css";
import React from "react";
import { useEffect, useState } from "react";
import parse from "html-react-parser";
import {
  Avatar,
  Button,
  ButtonGroup,
  Container,
  Dialog,
  DialogContent,
  DialogTitle,
  Grid,
  IconButton,
  List,
  ListItem,
  ListItemAvatar,
  ListItemButton,
  ListItemText,
  ListSubheader,
  Pagination,
  Tooltip,
  Typography,
} from "@mui/material";
import CommentIcon from "@mui/icons-material/Comment";
import ReadMoreIcon from "@mui/icons-material/ReadMore";
import ThumbUpAltIcon from "@mui/icons-material/ThumbUpAlt";
import { EmptyProps } from "../../Models/Shared/SharedInterfaces";
import { ForumState } from "../../Models/Forum/ForumInterfaces";
import {
  getPageOfForumPosts,
  togglePostLike,
  toggleReplyLike,
} from "../../Services/ForumService/ForumService";
import { generateStringAvatar } from "../../Services/HelperService/HelperService";
import { isLoggedIn } from "../../Services/AuthService/AuthService";
import { capitalizeStringParts } from "../../Services/HelperService/HelperService";

const ForumPage: React.FC<EmptyProps> = (props: EmptyProps) => {
  const [state, setState] = useState<ForumState>({
    posts: [],
    totalPageCount: 0,
    pagination: {
      pageIndex: 0,
      pageSize: 5,
      name: null,
      sortDirection: "DESC",
    },
    repliesDialogOpened: false,
    selectedPost: null,
  });
  const [loading, setIsLoading] = useState<boolean>(false);
  const [isUserLoggedIn, setIsLoggedIn] = useState<boolean>(false);

  useEffect(() => {
    setIsLoading(true);

    isLoggedIn()
      .then((result) => {
        setIsLoggedIn(result);
      })
      .catch(() => setIsLoggedIn(false));

    getPageOfForumPosts(state.pagination).then((pageModel) => {
      setState({
        ...state,
        posts: pageModel.payload,
        totalPageCount: pageModel.totalPageCount,
      });

      setIsLoading(false);
    });
  }, []);

  const toggleLikeOfPost = (id: string): void => {
    togglePostLike(id).then((result) => {
      if (!result) {
        return;
      }

      setState({
        ...state,
        posts: state.posts.map((post) => {
          if (post.id === id) {
            post.likes = post.likedByCurrentUser
              ? post.likes - 1
              : post.likes + 1;
            post.likedByCurrentUser = !post.likedByCurrentUser;
          }
          return post;
        }),
      });
    });
  };

  const toggleLikeOfReply = (id: string): void => {
    if (!state.selectedPost) {
      return;
    }

    toggleReplyLike(state.selectedPost.id, id).then((result) => {
      if (!result) {
        return;
      }

      let newPosts = state.posts.map((post) => {
        if (!state.selectedPost) {
          return post;
        }

        if (state.selectedPost.id === post.id) {
          post.replies = post.replies.map((reply) => {
            if (reply.id === id) {
              reply.likes = reply.likedByCurrentUser
                ? reply.likes - 1
                : reply.likes + 1;
              reply.likedByCurrentUser = !reply.likedByCurrentUser;
            }

            return reply;
          });
        }
        return post;
      });

      setState({
        ...state,
        posts: newPosts,
      });
    });
  };

  const loadPage = (event: React.ChangeEvent<unknown>, page: number) => {
    if (page > state.totalPageCount) {
      return;
    }

    setIsLoading(true);

    getPageOfForumPosts({ ...state.pagination, pageIndex: page - 1 }).then(
      (pageModel) => {
        setState({
          ...state,
          posts: pageModel.payload,
          pagination: {
            ...state.pagination,
            pageIndex: pageModel.pageIndex,
          },
          totalPageCount: pageModel.totalPageCount,
        });

        setIsLoading(false);
      }
    );
  };

  return (
    <div className="d-flex w-100 bg-dark bg-opacity-25 pt-5 mt-4 align-item-center body-container">
      <Container
        className="bg-light rounded my-4 d-flex justify-items-center flex-column"
        maxWidth={"md"}
      >
        <h1 className="display-5 border-bottom border-2 border-success col-6 mx-auto text-center">
          StudentBox Forums
        </h1>
        {loading ? null : (
          <>
            <List className="col-11 d-block mx-auto">
              {state.posts.map((post) => (
                <>
                  <ListItemButton
                    alignItems="flex-start"
                    key={post.id}
                    className="postListItem border border-bottom-0 m-0 rounded rounded-0 rounded-top"
                    href={`/forum/posts/${post.id}`}
                  >
                    <ListItemAvatar className="d-flex align-self-center flex-column">
                      {post.author.avatarUrl ? (
                        <Avatar
                          src={post.author.avatarUrl}
                          sx={{ height: 52, width: 52 }}
                          className="me-2 bg-light border border-2 border-light"
                        />
                      ) : (
                        <Avatar
                          {...generateStringAvatar(
                            post.author.username.toUpperCase(),
                            52
                          )}
                          className="me-2"
                        />
                      )}
                    </ListItemAvatar>
                    <ListItemText
                      primary={<strong>{post.title}</strong>}
                      secondary={
                        <Grid
                          container
                          direction={"column"}
                          justifyContent={"space-between"}
                          xs={10}
                        >
                          <Grid item>
                            <Typography
                              sx={{ display: "inline" }}
                              component="p"
                              variant="body2"
                              color="text.primary"
                            >
                              <span>
                                <span className="text-primary">
                                  {post.author.username}
                                </span>
                                {" on "}
                                <span className="fw-light text-secondary">
                                  {new Date(post.modifiedAt).toLocaleString()}
                                </span>
                              </span>
                              <br />
                            </Typography>
                          </Grid>
                          <Grid item>
                            <Typography
                              sx={{ display: "inline" }}
                              component="p"
                              variant="body2"
                              color="text.primary"
                            >
                              <CommentIcon fontSize={"small"} />
                              {`${post.replies.length}`}
                            </Typography>
                          </Grid>
                        </Grid>
                      }
                    />
                  </ListItemButton>
                  <ButtonGroup
                    variant={"text"}
                    fullWidth
                    className="mb-3 border border-top-0 mt-0 bg-secondary rounded rounded-0 rounded-bottom bg-opacity-10 "
                  >
                    <Button
                      disabled={!isUserLoggedIn}
                      onClick={() => toggleLikeOfPost(post.id)}
                      className="border-0"
                    >
                      <ThumbUpAltIcon
                        className={`likeButton me-2 ${
                          post.likedByCurrentUser
                            ? "likeButton-flipped text-danger"
                            : null
                        } `}
                      />
                      <span
                        className={`${
                          post.likedByCurrentUser ? "text-danger" : ""
                        }`}
                      >
                        {post.likedByCurrentUser ? "Unlike" : "Like"}
                      </span>
                    </Button>
                    <Button
                      onClick={() => {
                        setState({
                          ...state,
                          repliesDialogOpened: true,
                          selectedPost: post,
                        });
                      }}
                    >
                      <CommentIcon className="me-2" />
                      Replies
                    </Button>
                    <Button href={`/forum/posts/${post.id}`}>
                      <ReadMoreIcon className="me-2" />
                      Read More
                    </Button>
                  </ButtonGroup>
                </>
              ))}
            </List>
            <Pagination
              color={"primary"}
              count={state.totalPageCount}
              onChange={loadPage}
              className="my-3 justify-content-center d-flex"
              page={state.pagination.pageIndex + 1}
              disabled={loading}
            />
          </>
        )}

        <Dialog
          open={state.repliesDialogOpened}
          onClose={() => {
            setState({ ...state, repliesDialogOpened: false });
          }}
          maxWidth={"lg"}
          fullWidth
          scroll={"paper"}
        >
          <DialogTitle>
            <h1 className="display-6">{state.selectedPost?.title}</h1>
          </DialogTitle>
          <DialogContent>
            <Grid container direction={"column"}>
              <Grid
                item
                className="p-2 bg-secondary bg-opacity-25 text-dark rounded shadow-sm m-1"
              >
                {parse(state.selectedPost?.content ?? "")}
              </Grid>
              <Grid item>
                <div className="d-flex flex-row justify-content-start align-items-center">
                  <span className="me-2">Posted by: </span>
                  {state.selectedPost?.author.avatarUrl ? (
                    <Avatar
                      src={state.selectedPost?.author.avatarUrl}
                      sx={{ height: 32, width: 32 }}
                      className="me-2 bg-light border border-2 border-light"
                    />
                  ) : (
                    <Avatar
                      {...generateStringAvatar(
                        state.selectedPost?.author.username ?? "".toUpperCase(),
                        32
                      )}
                      className="me-2"
                    />
                  )}
                  {capitalizeStringParts(
                    state.selectedPost?.author.username ?? ""
                  )}
                </div>
              </Grid>
              <Grid item>
                <div className="d-flex flex-row justify-content-start align-items-center">
                  <span className="me-2">
                    {state.selectedPost?.createdAt !==
                    state.selectedPost?.modifiedAt
                      ? "Last Modified: "
                      : "Created On: "}
                  </span>
                  {new Date(
                    state.selectedPost?.createdAt !==
                    state.selectedPost?.modifiedAt
                      ? state.selectedPost?.modifiedAt ?? 0
                      : state.selectedPost?.createdAt ?? 0
                  ).toLocaleString()}
                </div>
              </Grid>
            </Grid>
            {state.selectedPost && state.selectedPost.replies.length ? (
              <>
                <h3 className="fw-light col-8 d-block mx-auto mt-4">
                  Replies:
                </h3>
                <List className="col-8 mx-auto">
                  {state.selectedPost?.replies.map((reply) => (
                    <>
                      <ListItem className="border border-1 rounded">
                        <ListItemAvatar className="d-flex align-self-center flex-column">
                          {reply.author.avatarUrl ? (
                            <Avatar
                              src={reply.author.avatarUrl}
                              sx={{ height: 52, width: 52 }}
                              className="me-2 bg-light border border-2 border-light"
                            />
                          ) : (
                            <Avatar
                              {...generateStringAvatar(
                                reply.author.username.toUpperCase(),
                                52
                              )}
                              className="me-2"
                            />
                          )}
                        </ListItemAvatar>
                        <ListItemText
                          primary={
                            <Typography
                              sx={{ display: "inline" }}
                              component="p"
                              variant="body2"
                              color="text.primary"
                            >
                              <span>
                                <span className="text-primary">
                                  {capitalizeStringParts(reply.author.username)}
                                </span>
                              </span>
                              <br />
                            </Typography>
                          }
                          secondary={parse(reply.content)}
                        />
                        <ListSubheader>
                          <Tooltip
                            title={`${
                              reply.likedByCurrentUser ? "Unlike" : "Like"
                            } the reply`}
                          >
                            <IconButton
                              disabled={!isUserLoggedIn}
                              onClick={() => toggleLikeOfReply(reply.id)}
                              className="border-0"
                            >
                              <ThumbUpAltIcon
                                className={`likeButton border-0 ${
                                  reply.likedByCurrentUser
                                    ? "likeButton-flipped text-danger"
                                    : "text-primary"
                                } `}
                              />
                            </IconButton>
                          </Tooltip>
                          <span>{reply.likes}</span>
                        </ListSubheader>
                      </ListItem>
                    </>
                  ))}
                </List>
              </>
            ) : (
              <h3 className="fw-light col-8 d-block mx-auto mt-4 text-center">
                This post has no replies.
              </h3>
            )}
          </DialogContent>
        </Dialog>
      </Container>
    </div>
  );
};

export default ForumPage;
