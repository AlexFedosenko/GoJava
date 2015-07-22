package ua.goit.kyrychok.kickstarter.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.goit.kyrychok.kickstarter.StandByMode;
import ua.goit.kyrychok.kickstarter.mvc.view.PaymentView;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PaymentControllerTest {

    @Mock
    private PaymentView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    public void whenShowModelThenRenderView() throws Exception {
        PaymentController controller = new PaymentController();
        controller.setView(view);
        controller.initCurrentMode(StandByMode.USER);

        controller.showModel();

        verify(view, times(1)).render(anyString());
    }

}