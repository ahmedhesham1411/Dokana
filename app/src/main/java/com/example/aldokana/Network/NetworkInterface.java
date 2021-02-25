package com.example.aldokana.Network;
import com.example.aldokana.Activities.CallUsRequest;
import com.example.aldokana.Model.AboutAppResponse;
import com.example.aldokana.Model.AddAddressRequestModel;
import com.example.aldokana.Model.AddToBasketRequest;
import com.example.aldokana.Model.AllRegionsMasterModel;
import com.example.aldokana.Model.BasketItemsResponse;
import com.example.aldokana.Model.BasketResponse;
import com.example.aldokana.Model.CategoriesResponse;
import com.example.aldokana.Model.CodeRequest;
import com.example.aldokana.Model.CodeResponse;
import com.example.aldokana.Model.CouponRequest;
import com.example.aldokana.Model.CouponResponse;
import com.example.aldokana.Model.DeleteBasketItemRequest;
import com.example.aldokana.Model.EditProfileResponse;
import com.example.aldokana.Model.FeesRequest;
import com.example.aldokana.Model.FeesResponse;
import com.example.aldokana.Model.LoginRequest;
import com.example.aldokana.Model.LoginResposne;
import com.example.aldokana.Model.NotificationResponse;
import com.example.aldokana.Model.OffersResponse;
import com.example.aldokana.Model.OrdersResponse;
import com.example.aldokana.Model.PrivacyPolicyResponse;
import com.example.aldokana.Model.ProductsResponse;
import com.example.aldokana.Model.QuestionAndAnswerResponse;
import com.example.aldokana.Model.RateRequest;
import com.example.aldokana.Model.RegisterationResponse;
import com.example.aldokana.Model.RegistrationRequest;
import com.example.aldokana.Model.SaveOrderRequest;
import com.example.aldokana.Model.SearchRequest;
import com.example.aldokana.Model.TermsResponse;
import com.example.aldokana.Model.User;
import com.example.aldokana.Model.UserAddressesResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkInterface {
    @POST("register")
    Observable<RegisterationResponse> Register(@Body RegistrationRequest registrationRequest);

    @POST("login")
    Observable<LoginResposne> Login(@Body LoginRequest loginRequest);

    @POST("sendCode")
    Observable<CodeResponse> SendCode(@Body CodeRequest codeRequest);

    @GET("profile")
    Observable<User> GetProfile();

    @Multipart
    @POST("update-profile")
    Observable<EditProfileResponse> updateProfile(@Part("name") RequestBody name,
                                                  @Part("phone") RequestBody phone,
                                                  @Part("email") RequestBody email,
                                                  @Part MultipartBody.Part photo);

    @GET("regions")
    Observable<AllRegionsMasterModel> GetRegions();

    @POST("addAddress")
    Observable<EditProfileResponse> AddAddress(@Body AddAddressRequestModel addAddressRequestModel);

    @GET("userAddresses")
    Observable<UserAddressesResponse> GetUserAddresses();

    @DELETE("deleteAddress/{id}")
    Observable<EditProfileResponse> DeleteAddress(@Path("id") int id);


    @GET("all-faq")
    Observable<QuestionAndAnswerResponse> GetQuestionsAndAnswers();

    @GET("about-us")
    Observable<AboutAppResponse> GetAboutApp();

    @GET("privacy-policy")
    Observable<PrivacyPolicyResponse> GetPrivacyPolicy();

    @GET("terms-conditions")
    Observable<TermsResponse> GetTerms();

    @POST("contact-us")
    Observable<EditProfileResponse> CallUs(@Body CallUsRequest callUsRequest);

    @GET("user-notifications")
    Observable<NotificationResponse> GetNotification();

    @DELETE("user-notifications/{id}")
    Observable<EditProfileResponse> DeleteNotification(@Path("id") int id);

    @GET("categories")
    Observable<CategoriesResponse> GetCategories();

    @GET("home")
    Observable<ProductsResponse> GetHome();

    @GET("category-products/{id}")
    Observable<ProductsResponse> GetFromCat(@Path("id") int id);

    @GET("companies")
    Observable<OffersResponse> GetCompanies();

    @GET("offer-product")
    Observable<ProductsResponse> GetOffers();

    @POST("addToBasket")
    Observable<EditProfileResponse> AddToBasket(@Body AddToBasketRequest addToBasketRequest);

    @GET("user-basket")
    Observable<BasketItemsResponse> GetUserBasket();

    @GET("myOrders")
    Observable<OrdersResponse> GetOrders();

    @GET("cancelOrder/{id}")
    Observable<EditProfileResponse> CancelOrder(@Path("id") int id);

    @GET("similar-product/{id}")
    Observable<ProductsResponse> GetSimilar(@Path("id") String serial);

    @POST("user-rate-product")
    Observable<EditProfileResponse> Rate(@Body RateRequest rateRequest);

    @POST("search")
    Observable<ProductsResponse> Search(@Body SearchRequest searchRequest);

    @GET("user-basket")
    Observable<BasketResponse> GetUserBasket2();

    @POST("deleteBasketItem")
    Observable<EditProfileResponse> DeleteBasketItem(@Body DeleteBasketItemRequest deleteBasketItemRequest);

    @POST("basket-shipping-fees")
    Observable<FeesResponse> GetFees(@Body FeesRequest feesRequest);

    @POST("checkCoupon")
    Observable<CouponResponse> CheckCoupon(@Body CouponRequest couponRequest);

    @DELETE("deleteAllBasket")
    Observable<EditProfileResponse> DeleteBasketItems();

    @POST("orderSave")
    Observable<EditProfileResponse> SaveOrder(@Body SaveOrderRequest saveOrderRequest);

    /*    @GET("v1/calendar")
    Observable<Prayer> getTiming(@Query("latitude") Double latitude,
                                 @Query("longitude") Double longitude,
                                 @Query("method") Integer method,
                                 @Query("month") Integer month,
                                 @Query("year") Integer year);*/
}
