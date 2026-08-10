package com.example.product_service;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraceDebugController {

    private static final Logger log = LoggerFactory.getLogger(TraceDebugController.class);

    private final ObjectProvider<Tracer> tracerProvider;

    public TraceDebugController(ObjectProvider<Tracer> tracerProvider) {
        this.tracerProvider = tracerProvider;
    }

    @GetMapping("/trace-test")
    public String traceTest() {
        log.info("trace-test endpoint called");
        return "trace ok";
    }

    @GetMapping("/manual-span")
    public String manualSpan() {
        Tracer tracer = tracerProvider.getIfAvailable();

        if (tracer == null) {
            log.error("NO TRACER BEAN");
            return "NO TRACER BEAN";
        }

        Span span = tracer.nextSpan().name("manual-span").start();

        try (Tracer.SpanInScope scope = tracer.withSpan(span)) {
            log.info("manual span created");
            return "MANUAL SPAN OK";
        } finally {
            span.end();
        }
    }}
