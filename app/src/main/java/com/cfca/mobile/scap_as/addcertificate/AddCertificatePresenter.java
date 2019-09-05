package com.cfca.mobile.scap_as.addcertificate;

import cfca.mobile.constant.CFCAPublicConstant;
import com.cfca.mobile.scap_as.data.CertificateSource;
import com.cfca.mobile.scap_as.util.Callback;

/**
 * Created by wufan on 2017/5/17.
 */

public class AddCertificatePresenter implements AddCertificateContract.Presenter {

    private final AddCertificateContract.View view;
    private final CertificateSource certificateSource;

    public AddCertificatePresenter(AddCertificateContract.View view, CertificateSource certificateSource) {
        this.view = view;
        this.certificateSource = certificateSource;
    }

    @Override
    public void start() {
        // do nothing
    }

    @Override
    public void stop() {
        certificateSource.cancelAddCertificate();
    }

    @Override
    public void addCertificate(CFCAPublicConstant.CERT_TYPE certType, String pin, CFCAPublicConstant.CERT_SYS certSys) {
        view.showLoading();
        certificateSource.addCertificate(certType, pin, certSys, new Callback() {
            @Override
            public void onSuccess() {
                view.hideLoading();
                view.showCertificateList();
            }

            @Override
            public void onFailure(Throwable e) {
                view.hideLoading();
                view.showAddCertificateFailure(e);
            }
        });
    }
}
